package io.emeraldpay.polkaj.examples.staking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import io.emeraldpay.polkaj.api.PolkadotApi;
import io.emeraldpay.polkaj.api.StandardCommands;
import io.emeraldpay.polkaj.api.StandardSubscriptions;
import io.emeraldpay.polkaj.apiws.JavaHttpSubscriptionAdapter;
import io.emeraldpay.polkaj.scale.ScaleExtract;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.MetadataReader;
import io.emeraldpay.polkaj.schnorrkel.Schnorrkel;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.tx.ExtrinsicContext;
import io.emeraldpay.polkaj.tx.StakingRequests;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.ByteData;
import io.emeraldpay.polkaj.types.DotAmount;
import io.emeraldpay.polkaj.types.DotAmountFormatter;
import io.emeraldpay.polkaj.types.Hash256;
import org.apache.commons.codec.binary.Hex;

/**
 * Request must be signed by the controller.
 */
public class StakingUnbond {

	private static final DotAmountFormatter AMOUNT_FORMAT = DotAmountFormatter.autoFormatter();


	public static void main(String[] args) throws Exception {
		String api = "ws://localhost:9944";
		if (args.length >= 1) {
			api = args[0];
		}
		System.out.println("Connect to: " + api);

		Schnorrkel.KeyPair controllerKey;
		Address controller;
		if (args.length >= 3) {
			System.out.println("Use provided addresses");
			controllerKey = Schnorrkel.getInstance().generateKeyPairFromSeed(Hex.decodeHex(args[1]));
		}
		else {
			System.out.println("Use standard accounts for Alice and Controller, expected to run against development network");
			controllerKey = Schnorrkel.getInstance().generateKeyPairFromSeed(
					Hex.decodeHex("b29604c55174c46aabefe5a4095d072b4b177781155c57886e3a18ebad3d4bf5")
			);
		}
		controller = new Address(SS58Type.Network.SUBSTRATE, controllerKey.getPublicKey());

		DotAmount amount = DotAmount.from(0.01, DotAmount.Westies);

		final JavaHttpSubscriptionAdapter adapter = JavaHttpSubscriptionAdapter.newBuilder().connectTo(api).build();
		try (PolkadotApi client = PolkadotApi.newBuilder().subscriptionAdapter(adapter).build()) {
			System.out.println("Connected: " + adapter.connect().get());

			// Subscribe to block heights
			AtomicLong height = new AtomicLong(0);
			CompletableFuture<Long> waitForBlocks = new CompletableFuture<>();
			client.subscribe(
					StandardSubscriptions.getInstance().newHeads()
			).get().handler(event -> {
				long current = event.getResult().getNumber();
				System.out.println("Current height: " + current);
				if (height.get() == 0) {
					height.set(current);
				}
				else {
					long blocks = current - height.get();
					if (blocks > 3) {
						waitForBlocks.complete(current);
					}
				}
			});

			// get current runtime metadata to correctly build the extrinsic
			Metadata metadata = client.execute(
							StandardCommands.getInstance().stateMetadata()
					)
					.thenApply(ScaleExtract.fromBytesData(new MetadataReader()))
					.get();

			// prepare context for execution
			ExtrinsicContext context = ExtrinsicContext.newAutoBuilder(controller, client)
					.get()
					.build();

			System.out.println("Using genesis  : " + context.getGenesis());
			System.out.println("Using runtime  : " + context.getTxVersion() + ", " + context.getRuntimeVersion());
			System.out.println("Using nonce    : " + context.getNonce());
			System.out.println("------");
			System.out.println("Unbond         : " + AMOUNT_FORMAT.format(amount) + " from " + controller);

			// prepare call, and sign with sender Secret Key within the context
			StakingRequests.UnbondTransfer unbondTransfer = StakingRequests.unbond()
					.runtime(metadata)
					.from(controller)
					.value(amount)
					.sign(controllerKey, context)
					.build();

			ByteData req = unbondTransfer.encodeRequest();
			System.out.println("RPC Request Payload: " + req);
			Hash256 txid = client.execute(
					StandardCommands.getInstance().authorSubmitExtrinsic(req)
			).get();
			System.out.println("Tx Hash: " + txid);

			// wait for a few blocks, to show how subscription to storage changes works, which will
			// notify about relevant updates during those blocks
			waitForBlocks.get();
		}
	}
}
