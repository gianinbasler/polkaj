package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scaletypes.MultiAddressWriter;

public class StakingBondTransferWriter implements ScaleWriter<StakingBondTransfer> {

	private final MultiAddressWriter addressWriter;

	private final RewardDestinationWriter rewardDestinationWriter;

	public StakingBondTransferWriter() {
		this.addressWriter = new MultiAddressWriter();
		this.rewardDestinationWriter = new RewardDestinationWriter();
	}

	@Override
	public void write(ScaleCodecWriter wrt, StakingBondTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.write(addressWriter, value.getControllerAddress());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
		wrt.write(rewardDestinationWriter, value.getPayee());
	}

}
