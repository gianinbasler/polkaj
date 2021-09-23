package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;

public class StakingWithdrawUnbondedTransferWriter implements ScaleWriter<StakingWithdrawUnbondedTransfer> {

	@Override
	public void write(ScaleCodecWriter wrt, StakingWithdrawUnbondedTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.writeUint32(value.getNumSlashingSpans());
	}

}
