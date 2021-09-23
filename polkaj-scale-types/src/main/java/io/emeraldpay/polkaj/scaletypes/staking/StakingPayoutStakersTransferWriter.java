package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scaletypes.MultiAddressWriter;

public class StakingPayoutStakersTransferWriter implements ScaleWriter<StakingPayoutStakersTransfer> {

	private final MultiAddressWriter addressWriter;

	private final EraIndexWriter eraIndexWriter;

	public StakingPayoutStakersTransferWriter() {
		this.addressWriter = new MultiAddressWriter();
		this.eraIndexWriter = new EraIndexWriter();
	}

	@Override
	public void write(ScaleCodecWriter wrt, StakingPayoutStakersTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.write(addressWriter, value.getValidatorStashAddress());
		wrt.write(eraIndexWriter, value.getEraIndex());
	}

}
