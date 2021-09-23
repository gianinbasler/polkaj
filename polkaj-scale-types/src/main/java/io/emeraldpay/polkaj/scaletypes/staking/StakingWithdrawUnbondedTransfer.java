package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.Module;
import io.emeraldpay.polkaj.scaletypes.TransferBase;

public class StakingWithdrawUnbondedTransfer extends TransferBase {

	/**
	 * Number of eras to consider
	 */
	private long numSlashingSpans;

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 */
	public void init(Metadata metadata) {
		init(metadata, Module.STAKING,"withdraw_unbonded");
	}

	public long getNumSlashingSpans() {
		return numSlashingSpans;
	}

	public void setNumSlashingSpans(long numSlashingSpans) {
		this.numSlashingSpans = numSlashingSpans;
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
		StakingWithdrawUnbondedTransfer that = (StakingWithdrawUnbondedTransfer) o;
		return numSlashingSpans == that.numSlashingSpans;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), numSlashingSpans);
	}

	@Override
	public String toString() {
		return "StakingWithdrawUnbondedTransfer{" +
				"numSlashingSpans=" + numSlashingSpans +
				'}';
	}
}
