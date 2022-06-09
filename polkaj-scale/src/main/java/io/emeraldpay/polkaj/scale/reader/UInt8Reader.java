package io.emeraldpay.polkaj.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

public class UInt8Reader implements ScaleReader<Integer> {

    @Override
    public Integer read(ScaleCodecReader rdr) {
        int result = 0;
        result += rdr.readUByte();
        return result;
    }

}
