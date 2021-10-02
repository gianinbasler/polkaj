package io.emeraldpay.polkaj.scaletypes;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;

public class AccountBondedReader implements ScaleReader<AccountBonded> {

    private final MultiAddressReader addressReader;

    public AccountBondedReader(SS58Type.Network network) {
        this.addressReader = new MultiAddressReader(network);
    }

    @Override
    public AccountBonded read(ScaleCodecReader rdr) {
        AccountBonded result = new AccountBonded();
        result.setControllerAddress(rdr.read(addressReader));
        return result;
    }
}
