package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.Module;
import io.emeraldpay.polkaj.scaletypes.MultiAddress;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.types.Address;

/**
 * Call to stake amount to a validator
 */
public class StakingPayoutStakersTransfer extends TransferBase {

	/**
	 * Validator stash address
	 */
	private UnionValue<MultiAddress> validatorStashAddress;

	/**
	 *  Era for which to pay out the staking rewards
	 *  May be any era between `[current_era - history_depth; current_era]`
	 */
	private EraIndex eraIndex;

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 */
	public void init(Metadata metadata) {
		init(metadata, Module.STAKING, "payout_stakers");
	}

	public UnionValue<MultiAddress> getValidatorStashAddress() {
		return validatorStashAddress;
	}

	public void setValidatorStashAddress(UnionValue<MultiAddress> validatorStashAddress) {
		this.validatorStashAddress = validatorStashAddress;
	}

	public void setValidatorStashAddress(Address validatorStashAddress) {
		this.validatorStashAddress = MultiAddress.AccountID.from(validatorStashAddress);
	}

	public EraIndex getEraIndex() {
		return eraIndex;
	}

	public void setEraIndex(EraIndex eraIndex) {
		this.eraIndex = eraIndex;
	}

	public void setEraIndex(int eraIndex) {
		this.eraIndex = new EraIndex(eraIndex);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		StakingPayoutStakersTransfer that = (StakingPayoutStakersTransfer) o;
		return eraIndex == that.eraIndex && Objects.equals(validatorStashAddress, that.validatorStashAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), validatorStashAddress, eraIndex);
	}

	@Override
	public String toString() {
		return "StakingPayoutStakersTransfer{" +
				"validatorStashAddress=" + validatorStashAddress +
				", era=" + eraIndex +
				'}';
	}
}
