package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.scaletypes.Module;
import io.emeraldpay.polkaj.scaletypes.MultiAddress;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.types.Address;

/**
 * Call to set a new controller
 */
public class StakingSetControllerTransfer extends TransferBase {

	/**
	 * Controller address
	 */
	private UnionValue<MultiAddress> controllerAddress;

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 */
	public void init(Metadata metadata) {
		init(metadata, Module.STAKING, "set_controller");
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
		StakingSetControllerTransfer that = (StakingSetControllerTransfer) o;
		return Objects.equals(controllerAddress, that.controllerAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), controllerAddress);
	}

	@Override
	public String toString() {
		return "StakingSetControllerTransfer{" +
				"controllerAddress=" + controllerAddress +
				"} " + super.toString();
	}
}
