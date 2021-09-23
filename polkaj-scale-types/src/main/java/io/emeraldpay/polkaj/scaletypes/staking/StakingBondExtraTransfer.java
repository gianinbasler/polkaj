package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.Module;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.types.DotAmount;

public class StakingBondExtraTransfer extends TransferBase {

	/**
	 * Maximum number of tokens to stake in addition to the already staked tokens
	 */
	private DotAmount maxAdditional;

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 */
	public void init(Metadata metadata) {
		init(metadata, Module.STAKING, "bond_extra");
	}

	public DotAmount getMaxAdditional() {
		return maxAdditional;
	}

	public void setMaxAdditional(DotAmount maxAdditional) {
		this.maxAdditional = maxAdditional;
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
		StakingBondExtraTransfer that = (StakingBondExtraTransfer) o;
		return Objects.equals(maxAdditional, that.maxAdditional);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), maxAdditional);
	}

	@Override
	public String toString() {
		return "StakingBondExtraTransfer{" +
				"maxAdditional=" + maxAdditional +
				'}';
	}
}
