package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Arrays;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;

public class RewardDestinationReader implements ScaleReader<UnionValue<RewardDestination>> {

	private final UnionReader<RewardDestination> reader;

	public RewardDestinationReader(SS58Type.Network network) {
		this.reader = new UnionReader<>(
				Arrays.asList(
						new RewardDestinationReader.TypeIDReader(),
						new RewardDestinationReader.TypeIDReader(),
						new RewardDestinationReader.TypeIDReader(),
						new RewardDestinationReader.AccountIDReader(network),
						new RewardDestinationReader.TypeIDReader()
				)
		);
	}

	@Override
	public UnionValue<RewardDestination> read(ScaleCodecReader rdr) {
		return rdr.read(reader);
	}

	static class TypeIDReader implements ScaleReader<RewardDestination> {

		@Override
		public RewardDestination read(ScaleCodecReader rdr) {
			if(!rdr.hasNext()){
				return new RewardDestination.TypeID(RewardDestination.Type.STAKED.getCode());
			}
			return new RewardDestination.TypeID(rdr.readUint8());
		}
	}

	static class AccountIDReader implements ScaleReader<RewardDestination> {

		private final SS58Type.Network network;

		public AccountIDReader(SS58Type.Network network) {
			this.network = network;
		}

		@Override
		public RewardDestination read(ScaleCodecReader rdr) {
			return new RewardDestination.AccountID(new Address(network, rdr.readUint256()));
		}
	}
}
