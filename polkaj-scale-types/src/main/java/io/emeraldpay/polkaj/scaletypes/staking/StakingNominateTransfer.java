package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.Module;
import io.emeraldpay.polkaj.scaletypes.MultiAddress;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.types.Address;

public class StakingNominateTransfer extends TransferBase {

	private List<UnionValue<MultiAddress>> targets;

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 */
	public void init(Metadata metadata) {
		init(metadata, Module.STAKING, "nominate");
	}

	public List<UnionValue<MultiAddress>> getTargets() {
		return targets;
	}

	public void setTargets(List<UnionValue<MultiAddress>> targets) {
		this.targets = targets;
	}

	public void setAddressTargets(List<Address> targets) {
		this.targets = targets.stream()
				.map(MultiAddress.AccountID::from)
				.collect(Collectors.toList());
	}

	public void addTarget(Address target) {
		if (targets == null) {
			targets = new ArrayList<>();
		}
		targets.add(MultiAddress.AccountID.from(target));
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
		StakingNominateTransfer that = (StakingNominateTransfer) o;
		return Objects.equals(targets, that.targets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), targets);
	}

	@Override
	public String toString() {
		return "StakingNominateTransfer{" +
				"targets=" + targets +
				'}';
	}
}
