package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;

public class StakingBondExtraTransferWriter implements ScaleWriter<StakingBondExtraTransfer> {

	@Override
	public void write(ScaleCodecWriter wrt, StakingBondExtraTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getMaxAdditional().getValue());
	}

}
