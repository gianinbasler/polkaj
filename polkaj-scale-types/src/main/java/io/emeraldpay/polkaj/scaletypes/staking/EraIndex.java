package io.emeraldpay.polkaj.scaletypes.staking;

import java.util.Objects;

public class EraIndex {

	private int value;

	public EraIndex(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EraIndex eraIndex = (EraIndex) o;
		return value == eraIndex.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "EraIndex{" +
				"value=" + value +
				'}';
	}
}
