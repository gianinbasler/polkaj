package io.emeraldpay.polkaj.scaletypes;

public enum Module {

	BALANCES("Balances"),
	STAKING("Staking");

	private final String name;

	Module(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
