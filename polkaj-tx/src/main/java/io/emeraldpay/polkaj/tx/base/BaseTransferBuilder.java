package io.emeraldpay.polkaj.tx.base;

import java.math.BigInteger;
import java.util.Arrays;

import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.schnorrkel.Schnorrkel;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.tx.ExtrinsicContext;
import io.emeraldpay.polkaj.tx.ExtrinsicSigner;
import io.emeraldpay.polkaj.tx.SignException;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

public abstract class BaseTransferBuilder<T extends BaseTransferBuilder<T, S>, S extends TransferBase> {

	protected Address from;

	protected Extrinsic.Signature signature;

	protected Long nonce;

	protected DotAmount tip;

	protected S call;

	protected ScaleWriter<S> writer;

	protected BaseTransferBuilder(S call, ScaleWriter<S> writer) {
		this.call = call;
		this.writer = writer;
	}

	protected abstract T getThis();

	public T runtime(Metadata metadata) {
		this.call.init(metadata);
		return getThis();
	}

	public T module(int moduleIndex, int callIndex) {
		call.setModuleIndex(moduleIndex);
		call.setCallIndex(callIndex);
		return getThis();
	}

	/**
	 * @param from sender address
	 * @return builder
	 */
	public T from(Address from) {
		this.from = from;
		if (this.tip == null) {
			// set default tip as well now that the network is known
			this.tip = new DotAmount(BigInteger.ZERO, from.getNetwork());
		}
		return getThis();
	}

	/**
	 * (optional) tip to include for the miner
	 *
	 * @param tip tip to use
	 * @return builder
	 */
	public T tip(DotAmount tip) {
		this.tip = tip;
		return getThis();
	}

	/**
	 * (optional) Set once, if setting a predefined signature.
	 * Otherwise, nonce is set during {@link #sign} operation
	 *
	 * @param nonce once to use
	 * @return builder
	 */
	public T nonce(Long nonce) {
		this.nonce = nonce;
		return getThis();
	}

	/**
	 * (optional) Set once provided with the context, if setting a presefined signature.
	 * Otherwise nonce is set during {@link #sign} operation
	 *
	 * @param context context with once to use
	 * @return builder
	 */
	public T nonce(ExtrinsicContext context) {
		return nonce(context.getNonce());
	}

	/**
	 * Set a predefined signature. Either this method, or {@link #sign} must be called
	 *
	 * @param signature precalculated signature
	 * @return builder
	 */
	public T signed(Extrinsic.Signature signature) {
		this.signature = signature;
		return getThis();
	}

	protected Extrinsic<S> buildExtrinsic() {
		Extrinsic.TransactionInfo tx = new Extrinsic.TransactionInfo();
		tx.setNonce(this.nonce);
		tx.setSender(this.from);
		tx.setSignature(buildSignature(this.signature));
		tx.setTip(this.tip);

		Extrinsic<S> extrinsic = new Extrinsic<>();
		extrinsic.setCall(this.call);
		extrinsic.setTx(tx);
		return extrinsic;
	}

	protected Extrinsic.Signature buildSignature(Extrinsic.Signature signature) {
		switch (signature.getType()) {
			case ED25519:
				return new Extrinsic.ED25519Signature(signature.getValue());
			case SR25519:
				return new Extrinsic.SR25519Signature(signature.getValue());
			default:
				String msg = String.format("Signature type %s is not supported", signature.getType());
				throw new UnsupportedOperationException(msg);
		}
	}

	/**
	 * Sign the transfer
	 *
	 * @param key     sender key pair
	 * @param context signing context
	 * @return builder
	 * @throws SignException         if signing is failed
	 * @throws IllegalStateException on data conflict
	 */
	public T sign(Schnorrkel.KeyPair key, ExtrinsicContext context) throws SignException {
		if (this.nonce != null && this.nonce != context.getNonce()) {
			throw new IllegalStateException(
					"Trying to sign with context with different nonce. Reset nonce, or provide the same value");
		}
		if (this.from != null) {
			if (!Arrays.equals(this.from.getPubkey(), key.getPublicKey())) {
				throw new SignException(
						"Cannot sign transfer from " + this.from + " by pubkey of " + new Address(this.from.getNetwork(),
								key.getPublicKey()));
			}
		}
		else {
			this.from = new Address(SS58Type.Network.LIVE, key.getPublicKey());
		}
		ExtrinsicSigner<S> signer = new ExtrinsicSigner<>(writer);
		return this.nonce(context)
				.signed(new Extrinsic.SR25519Signature(signer.sign(context, this.call, key)));
	}

}
