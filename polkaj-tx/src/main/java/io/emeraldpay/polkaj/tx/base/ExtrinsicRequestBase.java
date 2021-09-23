package io.emeraldpay.polkaj.tx.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.ExtrinsicWriter;
import io.emeraldpay.polkaj.scaletypes.TransferBase;
import io.emeraldpay.polkaj.tx.ExtrinsicRequest;
import io.emeraldpay.polkaj.types.ByteData;

public abstract class ExtrinsicRequestBase<T extends TransferBase> implements ExtrinsicRequest {

	private final ExtrinsicWriter<T> extrinsicWriter;

	private final Extrinsic<T> extrinsic;

	protected ExtrinsicRequestBase(ScaleWriter<T> codec, Extrinsic<T> extrinsic) {
		this.extrinsicWriter = new ExtrinsicWriter<>(codec);
		this.extrinsic = extrinsic;
	}

	public Extrinsic<T> getExtrinsic() {
		return extrinsic;
	}

	@Override
	public ByteData encodeRequest() throws IOException {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ScaleCodecWriter writer = new ScaleCodecWriter(buf);
		writer.write(extrinsicWriter, extrinsic);
		return new ByteData(buf.toByteArray());
	}

	@Override
	public String toString() {
		return "extrinsic=" + extrinsic;
	}
}
