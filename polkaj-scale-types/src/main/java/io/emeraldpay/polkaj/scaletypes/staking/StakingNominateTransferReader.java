package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scale.reader.ListReader;
import io.emeraldpay.polkaj.scaletypes.MultiAddress;
import io.emeraldpay.polkaj.scaletypes.MultiAddressReader;
import io.emeraldpay.polkaj.ss58.SS58Type;

public class StakingNominateTransferReader implements ScaleReader<StakingNominateTransfer> {

	private final ListReader<UnionValue<MultiAddress>> targetsReader;

	public StakingNominateTransferReader(SS58Type.Network network) {
		this.targetsReader = new ListReader<>(new MultiAddressReader(network));
	}

	@Override
	public StakingNominateTransfer read(ScaleCodecReader rdr) {
		StakingNominateTransfer result = new StakingNominateTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setTargets(rdr.read(targetsReader));
		return result;
	}
}
