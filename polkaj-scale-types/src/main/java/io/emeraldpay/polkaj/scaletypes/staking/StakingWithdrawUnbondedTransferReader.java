package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

public class StakingWithdrawUnbondedTransferReader implements ScaleReader<StakingWithdrawUnbondedTransfer> {

	@Override
	public StakingWithdrawUnbondedTransfer read(ScaleCodecReader rdr) {
		StakingWithdrawUnbondedTransfer result = new StakingWithdrawUnbondedTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setNumSlashingSpans(rdr.readUint32());
		return result;
	}

}
