package io.emeraldpay.polkaj.scaletypes.staking;

import java.io.IOException;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;

public class EraIndexWriter implements ScaleWriter<EraIndex> {

	@Override
	public void write(ScaleCodecWriter wrt, EraIndex eraIndex) throws IOException {
		wrt.writeCompact(eraIndex.getValue());
	}
}
