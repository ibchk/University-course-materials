package ee.taltech.iti0200.bonuscards.cards;

import ee.taltech.iti0200.bonuscards.Person;
import ee.taltech.iti0200.bonuscards.Store;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class RimiCard extends BonusCard {

    public static final double PERCENT = 0.02;

    BigDecimal balance = new BigDecimal("0");
    Store store;
    Person person;
    private CardType cardType = CardType.RIMI;

    RimiCard(Store store, Person person) {
        this.store = store;
        this.person = person;
    }

    /**
     * Collects bonus on the specified payment amount.
     *
     * @param paymentAmount the payment amount
     * @return collected bonus
     */
    @Override
    public BigDecimal collectBonus(double paymentAmount) {
        if (paymentAmount >= 10) {
            BigDecimal newpaymentAmount = new BigDecimal(paymentAmount).setScale(3, RoundingMode.UNNECESSARY);
            BigDecimal bonus = BigDecimal.valueOf(newpaymentAmount.doubleValue() * PERCENT);
            balance = getBonusBalance().add(bonus.setScale(3, RoundingMode.UNNECESSARY));
            return bonus;
        }
        return new BigDecimal(0);
    }

    public BigDecimal setBalance(BigDecimal newBalance) {
        balance = newBalance;
        return balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Store getCardStore() {
        return store;
    }

    public Person getCardPerson() {
        return person;
    }

    public CardType getCardType() {
        return cardType;
    }
}
