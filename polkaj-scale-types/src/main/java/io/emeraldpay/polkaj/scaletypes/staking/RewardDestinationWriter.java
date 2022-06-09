package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;
import java.util.Arrays;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;

public class RewardDestinationWriter implements ScaleWriter<UnionValue<RewardDestination>> {

	private static final UnionWriter<RewardDestination> WRITER = new UnionWriter<>(
			Arrays.asList(
					new RewardDestinationWriter.TypeIDWriter(),
					new RewardDestinationWriter.TypeIDWriter(),
					new RewardDestinationWriter.TypeIDWriter(),
					new RewardDestinationWriter.AccountIDWriter(),
					new RewardDestinationWriter.TypeIDWriter()
			)
	);

	@Override
	public void write(ScaleCodecWriter wrt, UnionValue<RewardDestination> value) throws IOException {
		wrt.write(WRITER, value);
	}

	static class TypeIDWriter implements ScaleWriter<RewardDestination> {

		@Override
		public void write(ScaleCodecWriter wrt, RewardDestination value) throws IOException {
			RewardDestination.TypeID typeID = (RewardDestination.TypeID) value;
			wrt.writeUint8(typeID.getTypeValue());
		}
	}

	static class AccountIDWriter implements ScaleWriter<RewardDestination> {

		@Override
		public void write(ScaleCodecWriter wrt, RewardDestination value) throws IOException {
			RewardDestination.AccountID accountID = (RewardDestination.AccountID) value;
			wrt.writeUint256(accountID.getAddress().getPubkey());
		}
	}
}
