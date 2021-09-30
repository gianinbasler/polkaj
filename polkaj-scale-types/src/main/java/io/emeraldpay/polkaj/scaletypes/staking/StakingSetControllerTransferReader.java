package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.MultiAddressReader;
import io.emeraldpay.polkaj.ss58.SS58Type;

public class StakingSetControllerTransferReader implements ScaleReader<StakingSetControllerTransfer> {

	private final MultiAddressReader controllerReader;

	public StakingSetControllerTransferReader(SS58Type.Network network) {
		this.controllerReader = new MultiAddressReader(network);
	}

	@Override
	public StakingSetControllerTransfer read(ScaleCodecReader rdr) {
		StakingSetControllerTransfer result = new StakingSetControllerTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setControllerAddress(rdr.read(controllerReader));
		return result;
	}

}
