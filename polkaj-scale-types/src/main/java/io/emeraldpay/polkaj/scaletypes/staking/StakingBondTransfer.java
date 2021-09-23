package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.Module;
import io.emeraldpay.polkaj.scaletypes.MultiAddress;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

/**
 * Call to stake amount to a validator
 */
public class StakingBondTransfer extends TransferBase {

	/**
	 * Controller address
	 */
	private UnionValue<MultiAddress> controllerAddress;

	/**
	 * Number of tokens to stake
	 */
	private DotAmount amount;

	/**
	 * Reward destination
	 */
	private UnionValue<RewardDestination> payee;

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 */
	public void init(Metadata metadata) {
		init(metadata, Module.STAKING, "bond");
	}

	public UnionValue<MultiAddress> getControllerAddress() {
		return controllerAddress;
	}

	public void setControllerAddress(UnionValue<MultiAddress> controllerAddress) {
		this.controllerAddress = controllerAddress;
	}

	public void setControllerAddress(Address controllerAddress) {
		this.controllerAddress = MultiAddress.AccountID.from(controllerAddress);
	}

	public DotAmount getAmount() {
		return amount;
	}

	public void setAmount(DotAmount amount) {
		this.amount = amount;
	}

	public UnionValue<RewardDestination> getPayee() {
		return payee;
	}

	public void setPayee(UnionValue<RewardDestination> payee) {
		this.payee = payee;
	}

	public void setPayee(RewardDestination.Type payeeType) {
		this.payee = RewardDestination.TypeID.from(payeeType);
	}

	public void setPayee(Address address) {
		this.payee = RewardDestination.AccountID.from(address);
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
		StakingBondTransfer that = (StakingBondTransfer) o;
		return Objects.equals(controllerAddress, that.controllerAddress) && Objects.equals(amount, that.amount)
				&& Objects.equals(payee, that.payee);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), controllerAddress, amount, payee);
	}

	@Override
	public String toString() {
		return "StakingBondTransfer{" +
				"controller=" + controllerAddress +
				", value=" + amount +
				", payee=" + payee +
				'}';
	}
}
