package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scale.writer.ListWriter;
import io.emeraldpay.polkaj.scaletypes.MultiAddress;
import io.emeraldpay.polkaj.scaletypes.MultiAddressWriter;

public class StakingNominateTransferWriter implements ScaleWriter<StakingNominateTransfer> {

	private final ListWriter<UnionValue<MultiAddress>> targetsWriter;

	public StakingNominateTransferWriter() {
		this.targetsWriter = new ListWriter<>(new MultiAddressWriter());
	}

	@Override
	public void write(ScaleCodecWriter wrt, StakingNominateTransfer value) throws IOException {
		wrt.writeByte(value.getModuleIndex());
		wrt.writeByte(value.getCallIndex());
		wrt.write(targetsWriter, value.getTargets());
	}
}
