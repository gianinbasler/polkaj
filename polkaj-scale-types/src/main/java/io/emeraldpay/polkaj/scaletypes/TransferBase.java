package io.emeraldpay.polkaj.scaletypes;

public abstract class TransferBase extends ExtrinsicCall {

	protected TransferBase() {
		super();
	}

	protected TransferBase(Metadata metadata) {
		this();
		init(metadata);
	}

	protected TransferBase(int moduleIndex, int callIndex) {
		super(moduleIndex, callIndex);
	}

	/**
	 * Initialize call index from Runtime Metadata
	 *
	 * @param metadata current Runtime
	 * @param module the module in which the call to execute resides
	 * @param callName name of the call to execute
	 */
	public void init(Metadata metadata, Module module, String callName) {
		Metadata.Call call = metadata.findCall(module.getName(), callName)
				.orElseThrow(() -> new IllegalStateException("Call '" + module.getName() + "." + callName + "' doesn't exist"));
		init(call);
	}

	public abstract void init(Metadata metadata);

}
