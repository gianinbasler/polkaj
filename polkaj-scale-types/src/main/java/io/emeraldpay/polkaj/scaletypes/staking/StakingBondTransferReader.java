package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scaletypes.MultiAddressReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;

public class StakingBondTransferReader implements ScaleReader<StakingBondTransfer> {

	private final MultiAddressReader controllerReader;

	private final RewardDestinationReader rewardDestinationReader;

	private final SS58Type.Network network;

	public StakingBondTransferReader(SS58Type.Network network) {
		this.controllerReader = new MultiAddressReader(network);
		this.rewardDestinationReader = new RewardDestinationReader(network);
		this.network = network;
	}

	@Override
	public StakingBondTransfer read(ScaleCodecReader rdr) {
		StakingBondTransfer result = new StakingBondTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setControllerAddress(rdr.read(controllerReader));
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT), network));
		UnionValue<RewardDestination> test = rdr.read(rewardDestinationReader);
		result.setPayee(RewardDestination.Type.from(test.getIndex()));
		return result;
	}

}
