package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scaletypes.MultiAddressWriter;

public class StakingSetControllerTransferWriter implements ScaleWriter<StakingSetControllerTransfer> {

	private final MultiAddressWriter addressWriter;

	public StakingSetControllerTransferWriter() {
		this.addressWriter = new MultiAddressWriter();
	}

	@Override
	public void write(ScaleCodecWriter wrt, StakingSetControllerTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.write(addressWriter, value.getControllerAddress());
	}

}
