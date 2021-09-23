package io.emeraldpay.polkaj.tx;

import java.util.List;

import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.staking.RewardDestination;
import io.emeraldpay.polkaj.scaletypes.staking.StakingBondExtraTransfer;
import io.emeraldpay.polkaj.scaletypes.staking.StakingBondExtraTransferWriter;
import io.emeraldpay.polkaj.scaletypes.staking.StakingBondTransfer;
import io.emeraldpay.polkaj.scaletypes.staking.StakingBondTransferWriter;
import io.emeraldpay.polkaj.scaletypes.staking.StakingNominateTransfer;
import io.emeraldpay.polkaj.scaletypes.staking.StakingNominateTransferWriter;
import io.emeraldpay.polkaj.scaletypes.staking.StakingPayoutStakersTransfer;
import io.emeraldpay.polkaj.scaletypes.staking.StakingPayoutStakersTransferWriter;
import io.emeraldpay.polkaj.scaletypes.staking.StakingUnbondTransfer;
import io.emeraldpay.polkaj.scaletypes.staking.StakingUnbondTransferWriter;
import io.emeraldpay.polkaj.scaletypes.staking.StakingWithdrawUnbondedTransfer;
import io.emeraldpay.polkaj.scaletypes.staking.StakingWithdrawUnbondedTransferWriter;
import io.emeraldpay.polkaj.tx.base.BaseTransferBuilder;
import io.emeraldpay.polkaj.tx.base.ExtrinsicRequestBase;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

/**
 * Common requests and extrinsics specific to staking.
 */
public class StakingRequests {

	private StakingRequests() {
	}

	public static BondTransferBuilder bond() {
		return new BondTransferBuilder();
	}

	public static BondExtraTransferBuilder bondExtra() {
		return new BondExtraTransferBuilder();
	}

	public static NominateTransferBuilder nominate() {
		return new NominateTransferBuilder();
	}

	public static UnbondTransferBuilder unbond() {
		return new UnbondTransferBuilder();
	}

	public static WithdrawUnbondedTransferBuilder withdrawUnbonded() {
		return new WithdrawUnbondedTransferBuilder();
	}

	public static PayoutStakersTransferBuilder payoutStakers() {
		return new PayoutStakersTransferBuilder();
	}

	public static class BondTransfer extends ExtrinsicRequestBase<StakingBondTransfer> {

		public BondTransfer(Extrinsic<StakingBondTransfer> extrinsic) {
			super(new StakingBondTransferWriter(), extrinsic);
		}

		@Override
		public String toString() {
			return "BondTransfer{" + super.toString() + '}';
		}
	}

	public static class BondExtraTransfer extends ExtrinsicRequestBase<StakingBondExtraTransfer> {

		public BondExtraTransfer(Extrinsic<StakingBondExtraTransfer> extrinsic) {
			super(new StakingBondExtraTransferWriter(), extrinsic);
		}

		@Override
		public String toString() {
			return "BondExtraTransfer{" + super.toString() + '}';
		}
	}

	public static class NominateTransfer extends ExtrinsicRequestBase<StakingNominateTransfer> {

		public NominateTransfer(Extrinsic<StakingNominateTransfer> extrinsic) {
			super(new StakingNominateTransferWriter(), extrinsic);
		}

		@Override
		public String toString() {
			return "NominateTransfer{" + super.toString() + '}';
		}
	}

	public static class UnbondTransfer extends ExtrinsicRequestBase<StakingUnbondTransfer> {

		public UnbondTransfer(Extrinsic<StakingUnbondTransfer> extrinsic) {
			super(new StakingUnbondTransferWriter(), extrinsic);
		}

		@Override
		public String toString() {
			return "UnbondTransfer{" + super.toString() + '}';
		}
	}

	public static class WithdrawUnbondedTransfer extends ExtrinsicRequestBase<StakingWithdrawUnbondedTransfer> {

		public WithdrawUnbondedTransfer(Extrinsic<StakingWithdrawUnbondedTransfer> extrinsic) {
			super(new StakingWithdrawUnbondedTransferWriter(), extrinsic);
		}

		@Override
		public String toString() {
			return "WithdrawUnbondedTransfer{" + super.toString() + '}';
		}
	}

	public static class PayoutStakersTransfer extends ExtrinsicRequestBase<StakingPayoutStakersTransfer> {

		public PayoutStakersTransfer(Extrinsic<StakingPayoutStakersTransfer> extrinsic) {
			super(new StakingPayoutStakersTransferWriter(), extrinsic);
		}

		@Override
		public String toString() {
			return "PayoutStakersTransfer{" + super.toString() + '}';
		}
	}

	public static class BondTransferBuilder
			extends BaseTransferBuilder<BondTransferBuilder, StakingBondTransfer> {

		public BondTransferBuilder() {
			super(new StakingBondTransfer(), new StakingBondTransferWriter());
		}

		@Override
		protected BondTransferBuilder getThis() {
			return this;
		}

		public StakingRequests.BondTransferBuilder controller(Address controller) {
			this.call.setControllerAddress(controller);
			return this;
		}

		public StakingRequests.BondTransferBuilder amount(DotAmount amount) {
			this.call.setAmount(amount);
			return this;
		}

		public StakingRequests.BondTransferBuilder payee(RewardDestination.Type payeeType) {
			this.call.setPayee(payeeType);
			return this;
		}

		public StakingRequests.BondTransferBuilder payee(Address address) {
			this.call.setPayee(address);
			return this;
		}

		public BondTransfer build() {
			Extrinsic<StakingBondTransfer> extrinsic = buildExtrinsic();
			return new StakingRequests.BondTransfer(extrinsic);
		}
	}

	public static class BondExtraTransferBuilder
			extends BaseTransferBuilder<BondExtraTransferBuilder, StakingBondExtraTransfer> {

		public BondExtraTransferBuilder() {
			super(new StakingBondExtraTransfer(), new StakingBondExtraTransferWriter());
		}

		@Override
		protected BondExtraTransferBuilder getThis() {
			return this;
		}

		public StakingRequests.BondExtraTransferBuilder maxAdditional(DotAmount amount) {
			this.call.setMaxAdditional(amount);
			return this;
		}

		public BondExtraTransfer build() {
			Extrinsic<StakingBondExtraTransfer> extrinsic = buildExtrinsic();
			return new StakingRequests.BondExtraTransfer(extrinsic);
		}
	}

	public static class NominateTransferBuilder
			extends BaseTransferBuilder<NominateTransferBuilder, StakingNominateTransfer> {

		public NominateTransferBuilder() {
			super(new StakingNominateTransfer(), new StakingNominateTransferWriter());
		}

		@Override
		protected NominateTransferBuilder getThis() {
			return this;
		}

		public StakingRequests.NominateTransferBuilder targets(List<Address> targets) {
			this.call.setAddressTargets(targets);
			return this;
		}

		public StakingRequests.NominateTransferBuilder target(Address target) {
			this.call.addTarget(target);
			return this;
		}

		public NominateTransfer build() {
			Extrinsic<StakingNominateTransfer> extrinsic = buildExtrinsic();
			return new StakingRequests.NominateTransfer(extrinsic);
		}
	}

	public static class UnbondTransferBuilder
			extends BaseTransferBuilder<UnbondTransferBuilder, StakingUnbondTransfer> {

		public UnbondTransferBuilder() {
			super(new StakingUnbondTransfer(), new StakingUnbondTransferWriter());
		}

		@Override
		protected UnbondTransferBuilder getThis() {
			return this;
		}

		public StakingRequests.UnbondTransferBuilder value(DotAmount amount) {
			this.call.setValue(amount);
			return this;
		}

		public UnbondTransfer build() {
			Extrinsic<StakingUnbondTransfer> extrinsic = buildExtrinsic();
			return new StakingRequests.UnbondTransfer(extrinsic);
		}
	}

	public static class WithdrawUnbondedTransferBuilder
			extends BaseTransferBuilder<WithdrawUnbondedTransferBuilder, StakingWithdrawUnbondedTransfer> {

		public WithdrawUnbondedTransferBuilder() {
			super(new StakingWithdrawUnbondedTransfer(), new StakingWithdrawUnbondedTransferWriter());
		}

		@Override
		protected WithdrawUnbondedTransferBuilder getThis() {
			return this;
		}

		public StakingRequests.WithdrawUnbondedTransferBuilder numSlashingSpans(long numSlashingSpans) {
			this.call.setNumSlashingSpans(numSlashingSpans);
			return this;
		}

		public WithdrawUnbondedTransfer build() {
			Extrinsic<StakingWithdrawUnbondedTransfer> extrinsic = buildExtrinsic();
			return new StakingRequests.WithdrawUnbondedTransfer(extrinsic);
		}
	}

	public static class PayoutStakersTransferBuilder
			extends BaseTransferBuilder<PayoutStakersTransferBuilder, StakingPayoutStakersTransfer> {

		public PayoutStakersTransferBuilder() {
			super(new StakingPayoutStakersTransfer(), new StakingPayoutStakersTransferWriter());
		}

		@Override
		protected PayoutStakersTransferBuilder getThis() {
			return this;
		}

		public StakingRequests.PayoutStakersTransferBuilder validatorStashAddress(Address validatorStashAddress) {
			this.call.setValidatorStashAddress(validatorStashAddress);
			return this;
		}

		public StakingRequests.PayoutStakersTransferBuilder eraIndex(int eraIndex) {
			this.call.setEraIndex(eraIndex);
			return this;
		}

		public PayoutStakersTransfer build() {
			Extrinsic<StakingPayoutStakersTransfer> extrinsic = buildExtrinsic();
			return new StakingRequests.PayoutStakersTransfer(extrinsic);
		}
	}
}
