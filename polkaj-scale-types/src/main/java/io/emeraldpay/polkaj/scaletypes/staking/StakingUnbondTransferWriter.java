package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;

public class StakingUnbondTransferWriter implements ScaleWriter<StakingUnbondTransfer> {

	@Override
	public void write(ScaleCodecWriter wrt, StakingUnbondTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getValue().getValue());
	}

}
