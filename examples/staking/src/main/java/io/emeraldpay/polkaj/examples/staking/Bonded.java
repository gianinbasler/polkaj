package io.emeraldpay.polkaj.examples.staking;

import io.emeraldpay.polkaj.api.PolkadotApi;
import io.emeraldpay.polkaj.apihttp.JavaHttpAdapter;
import io.emeraldpay.polkaj.scaletypes.AccountBonded;
import io.emeraldpay.polkaj.scaletypes.AccountInfo;
import io.emeraldpay.polkaj.tx.AccountRequests;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;
import io.emeraldpay.polkaj.types.DotAmountFormatter;

public class Bonded {

    public static void main(String[] args) throws Exception {
        // TODO 30-Sep-2021/gianin: switch back to localhost
        //try (PolkadotApi client = PolkadotApi.newBuilder().rpcCallAdapter(JavaHttpAdapter.newBuilder().build()).build()) {
        String nodeUrl = "https://westend.api.onfinality.io/public";
        try (PolkadotApi client =
                PolkadotApi.newBuilder().rpcCallAdapter(JavaHttpAdapter.newBuilder().connectTo(nodeUrl).build()).build()) {

            DotAmountFormatter formatter = DotAmountFormatter.autoFormatter();

            Address address = Address.from("5F7Cs5VGkiD9L6EPWv7TxPxdSpV8cxuuUXgKcV15q93sznq2");
            System.out.println("Address: " + address);

            AccountInfo accountInfo = AccountRequests.balanceOf(address).execute(client).get();

            StringBuilder status = new StringBuilder();
            status
                    .append("Balance: ")
                    .append(formatter.format(accountInfo.getData().getFree()));

            if (!accountInfo.getData().getFeeFrozen().equals(DotAmount.ZERO)
                    || !accountInfo.getData().getMiscFrozen().equals(DotAmount.ZERO)) {
                status.append(" (frozen ")
                        .append(formatter.format(accountInfo.getData().getFeeFrozen()))
                        .append(" for Fee, frozen ")
                        .append(formatter.format(accountInfo.getData().getMiscFrozen()))
                        .append(" for Misc.)");
            }

            System.out.println(accountInfo);
            System.out.println(status);

            AccountBonded bonded = AccountRequests.bonded(address).execute(client).get();
            if (bonded == null) {
                System.out.println("Not bonded");
                return;
            }
        }
    }
}
