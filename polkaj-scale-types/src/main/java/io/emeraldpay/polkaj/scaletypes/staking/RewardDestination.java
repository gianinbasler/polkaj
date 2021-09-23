package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

import io.emeraldpay.polkaj.scale.UnionValue;
import io.emeraldpay.polkaj.types.Address;

public interface RewardDestination {

	enum Type {
		STAKED(0),
		STASH(1),
		CONTROLLER(2),
		ACCOUNT(3),
		NONE(4);

		private final int code;

		Type(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * Helper to create a UnionValue from a concrete RewardDestination instance
	 *
	 * @param type a valid RewardDestination.Type constant
	 * @param value concrete RewardDestination instance
	 * @return union value for the passed RewardDestination
	 */
	static UnionValue<RewardDestination> from(int type, RewardDestination value) {
		return new UnionValue<>(type, value);
	}

	class TypeID implements RewardDestination {

		private final int typeValue;

		public TypeID(int typeValue) {
			this.typeValue = typeValue;
		}

		public int getTypeValue() {
			return typeValue;
		}

		public static UnionValue<RewardDestination> from(Type payeeType) {
			return RewardDestination.from(payeeType.getCode(), null);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			TypeID typeID = (TypeID) o;
			return typeValue == typeID.typeValue;
		}

		@Override
		public int hashCode() {
			return Objects.hash(typeValue);
		}

		@Override
		public String toString() {
			return "TypeID{" +
					"typeValue=" + typeValue +
					'}';
		}
	}

	/**
	 * First MultiAddress type, used as a wrapper of a standard Address object
	 */
	class AccountID implements RewardDestination {

		private final Address address;

		public AccountID(Address address) {
			this.address = address;
		}

		public Address getAddress() {
			return address;
		}

		/**
		 * Transforms a standard Address object into a fully wrapped RewardDestination UnionValue
		 * @param address the address to use as destination for the rewards
		 * @return union value for the given address
		 */
		public static UnionValue<RewardDestination> from(Address address) {
			return RewardDestination.from(Type.ACCOUNT.getCode(), new AccountID(address));
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof AccountID)) return false;
			if (!((AccountID)o).canEquals(this)) return false;
			AccountID that = (AccountID) o;
			return Objects.equals(address, that.address);
		}

		@Override
		public int hashCode() {
			return Objects.hash(address);
		}

		public boolean canEquals(Object o) {
			return (o instanceof AccountID);
		}

		@Override
		public String toString() {
			return "AccountID{" +
					"address=" + address +
					'}';
		}
	}

}
