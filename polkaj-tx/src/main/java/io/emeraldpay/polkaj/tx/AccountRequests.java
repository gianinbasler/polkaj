package io.emeraldpay.polkaj.tx;

import java.nio.ByteBuffer;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scaletypes.AccountInfo;
import io.emeraldpay.polkaj.scaletypes.AccountInfoReader;
import io.emeraldpay.polkaj.scaletypes.BalanceReader;
import io.emeraldpay.polkaj.scaletypes.BalanceTransfer;
import io.emeraldpay.polkaj.scaletypes.BalanceTransferWriter;
import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.tx.base.BaseTransferBuilder;
import io.emeraldpay.polkaj.tx.base.ExtrinsicRequestBase;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.ByteData;
import io.emeraldpay.polkaj.types.DotAmount;

/**
 * Common requests and extrinsics specific to accounts.
 */
public class AccountRequests {

    private AccountRequests() {
    }

    /**
     * Get total blockchain issuance
     * @return total issuance reader
     */
    public static TotalIssuance totalIssuance() {
        return new TotalIssuance();
    }

    /**
     * Get current balance
     *
     * @param address address
     * @return balance reader
     */
    public static AddressBalance balanceOf(Address address) {
        return new AddressBalance(address);
    }

    /**
     * Transfer value from one account to another
     * @return builder for transfer
     */
    public static TransferBuilder transfer() {
        return new TransferBuilder();
    }

    /**
     * Transfer value from one account to another, but making sure that the balance of both accounts is above the existential
     * deposit
     *
     * @return builder for transfer-keep-alive
     */
    public static TransferKeepAliveBuilder transferKeepAlive() {
        return new TransferKeepAliveBuilder();
    }

    public static class TotalIssuance extends StorageRequest<DotAmount> {

        @Override
        public ByteData encodeRequest() {
            String key1 = "Balances";
            String key2 = "TotalIssuance";
            int len = 16 + 16;
            ByteBuffer buffer = ByteBuffer.allocate(len);
            Hashing.xxhash128(buffer, key1);
            Hashing.xxhash128(buffer, key2);
            return new ByteData(buffer.flip().array());
        }

        @Override
        public DotAmount apply(ByteData result) {
            return new ScaleCodecReader(result.getBytes()).read(new BalanceReader());
        }
    }

    public static class AddressBalance extends StorageRequest<AccountInfo> {

        private final Address address;

        public AddressBalance(Address address) {
            this.address = address;
        }

        @Override
        public ByteData encodeRequest() {
            String key1 = "System";
            String key2 = "Account";
            int len = 16 + 16 + 16 + 32;
            ByteBuffer buffer = ByteBuffer.allocate(len);
            Hashing.xxhash128(buffer, key1);
            Hashing.xxhash128(buffer, key2);
            Hashing.blake2128(buffer, address);
            buffer.put(address.getPubkey());
            return new ByteData(buffer.flip().array());
        }

        @Override
        public AccountInfo apply(ByteData result) {
            if (result == null) {
                return null;
            }
            return new ScaleCodecReader(result.getBytes()).read(new AccountInfoReader(address.getNetwork()));
        }
    }

    public static class Transfer extends ExtrinsicRequestBase<BalanceTransfer> {

		public Transfer(Extrinsic<BalanceTransfer> extrinsic) {
            super(new BalanceTransferWriter(), extrinsic);
        }

		@Override
		public String toString() {
			return "Transfer{" + super.toString() + '}';
		}
    }


    public static class TransferBuilder extends BaseTransferBuilder<TransferBuilder, BalanceTransfer> {

        public TransferBuilder() {
            super(new BalanceTransfer(), new BalanceTransferWriter());
        }

        @Override
        protected TransferBuilder getThis() {
            return this;
        }

        /**
         *
         * @param to recipient address
         * @return builder
         */
        public TransferBuilder to(Address to) {
            this.call.setDestination(to);
            return this;
        }

        /**
         *
         * @param amount amount to transfer
         * @return builder
         */
        public TransferBuilder amount(DotAmount amount) {
            this.call.setBalance(amount);
            return this;
        }

        /**
         *
         * @return signed Transfer
         */
        public Transfer build() {
            Extrinsic<BalanceTransfer> extrinsic = super.buildExtrinsic();
            return new Transfer(extrinsic);
        }
    }

    public static final class TransferKeepAliveBuilder extends TransferBuilder {

        private static final String TRANSFER_KEEP_ALIVE = "transfer_keep_alive";

        @Override
        public TransferBuilder runtime(Metadata metadata) {
            this.call.init(metadata, TRANSFER_KEEP_ALIVE);
            return this;
        }
    }

}
