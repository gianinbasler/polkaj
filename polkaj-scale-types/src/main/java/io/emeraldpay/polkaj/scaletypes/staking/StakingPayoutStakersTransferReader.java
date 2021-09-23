package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.MultiAddressReader;
import io.emeraldpay.polkaj.ss58.SS58Type;

public class StakingPayoutStakersTransferReader implements ScaleReader<StakingPayoutStakersTransfer> {

	private final MultiAddressReader validatorStashReader;

	private final EraIndexReader eraIndexReader;

	public StakingPayoutStakersTransferReader(SS58Type.Network network) {
		this.validatorStashReader = new MultiAddressReader(network);
		this.eraIndexReader = new EraIndexReader();
	}

	@Override
	public StakingPayoutStakersTransfer read(ScaleCodecReader rdr) {
		StakingPayoutStakersTransfer result = new StakingPayoutStakersTransfer();
		result.setModuleIndex(rdr.readUByte());
		result.setCallIndex(rdr.readUByte());
		result.setValidatorStashAddress(rdr.read(validatorStashReader));
		result.setEraIndex(rdr.read(eraIndexReader));
		return result;
	}

}
