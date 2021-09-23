package io.emeraldpay.polkaj.scaletypes.staking;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

public class EraIndexReader implements ScaleReader<EraIndex> {

	@Override
	public EraIndex read(ScaleCodecReader rdr) {
		return new EraIndex(rdr.readCompactInt());
	}
}
