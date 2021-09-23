package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;

public class StakingBondExtraTransferReader implements ScaleReader<StakingBondExtraTransfer> {

	private final SS58Type.Network network;

	public StakingBondExtraTransferReader(SS58Type.Network network) {
		this.network = network;
	}

	@Override
	public StakingBondExtraTransfer read(ScaleCodecReader rdr) {
		StakingBondExtraTransfer result = new StakingBondExtraTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setMaxAdditional(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT), network));
		return result;
	}

}
