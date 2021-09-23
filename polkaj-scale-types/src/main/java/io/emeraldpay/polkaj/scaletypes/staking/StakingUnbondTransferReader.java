package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;

public class StakingUnbondTransferReader implements ScaleReader<StakingUnbondTransfer> {

	private final SS58Type.Network network;

	public StakingUnbondTransferReader(SS58Type.Network network) {
		this.network = network;
	}

	@Override
	public StakingUnbondTransfer read(ScaleCodecReader rdr) {
		StakingUnbondTransfer result = new StakingUnbondTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setValue(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT), network));
		return result;
	}

}
