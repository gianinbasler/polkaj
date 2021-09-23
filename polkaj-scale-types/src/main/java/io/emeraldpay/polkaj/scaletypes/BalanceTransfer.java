package io.emeraldpay.polkaj.scaletypes;

import java.util.Objects;

import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

/**
 * Call to transfer [part of] balance to another address
 */
public class BalanceTransfer extends TransferBase {

    /**
     * Destination address
     */
    private UnionValue<MultiAddress> destination;
    /**
     * Balance to transfer
     */
    private DotAmount balance;

    /**
     * Initialize call index for given call of the Balance module from Runtime Metadata
     *
     * @param metadata current Runtime
     */
    public void init(Metadata metadata) {
        init(metadata, Module.BALANCES, "transfer");
    }

     /**
     * Initialize call index for given call of the Balance module from Runtime Metadata
     *
     * @param metadata current Runtime
     * @param callName name of the call to execute, e.g. transfer, transfer_keep_alive, or transfer_all
     */
    public void init(Metadata metadata, String callName) {
        init(metadata, Module.BALANCES, callName);
    }

    public UnionValue<MultiAddress> getDestination() {
        return destination;
    }

    public void setDestination(UnionValue<MultiAddress> destination) {
        this.destination = destination;
    }

    public void setDestination(Address destination) {
        this.destination = MultiAddress.AccountID.from(destination);
    }

    public DotAmount getBalance() {
        return balance;
    }

    public void setBalance(DotAmount balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BalanceTransfer)) return false;
        if (!super.equals(o)) return false;
        BalanceTransfer that = (BalanceTransfer) o;
        return Objects.equals(destination, that.destination) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), destination, balance);
    }

    @Override
    public boolean canEquals(Object o) {
        return (o instanceof BalanceTransfer);
    }

    @Override
    public String toString() {
        return "BalanceTransfer{" +
                "destination=" + destination +
                ", balance=" + balance +
                '}';
    }
}
