package io.emeraldpay.polkaj.scaletypes;

import java.util.Objects;

import io.emeraldpay.polkaj.scale.UnionValue;

public class AccountBonded {

    private UnionValue<MultiAddress> controllerAddress;

    public UnionValue<MultiAddress> getControllerAddress() {
        return controllerAddress;
    }

    public void setControllerAddress(UnionValue<MultiAddress> controllerAddress) {
        this.controllerAddress = controllerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountBonded that = (AccountBonded) o;
        return Objects.equals(controllerAddress, that.controllerAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controllerAddress);
    }

    @Override
    public String toString() {
        return "AccountBonded{" +
                "controllerAddress=" + controllerAddress +
                '}';
    }
}
