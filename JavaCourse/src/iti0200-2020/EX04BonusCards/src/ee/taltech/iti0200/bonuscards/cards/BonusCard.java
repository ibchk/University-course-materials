package ee.taltech.iti0200.bonuscards.cards;

import ee.taltech.iti0200.bonuscards.exceptions.AlreadyExistingCardTypeException;
import ee.taltech.iti0200.bonuscards.exceptions.BonusException;

import ee.taltech.iti0200.bonuscards.Person;
import ee.taltech.iti0200.bonuscards.Store;

import java.math.BigDecimal;

public abstract class BonusCard {
    public enum CardType { COOP, RIMI }

    /**
     * Creates a bonus card.
     *
     * @param cardType the card type to create
     * @param store    the store to add the card to
     * @param person   the person to add the card to
     * @return the bonus card that was created
     */
    public static BonusCard createCard(CardType cardType, Store store, Person person) {
        if (person.getBonusCardByType(cardType).isPresent()) {
            throw new AlreadyExistingCardTypeException();
        }
        store.addCustomer(person);
        if (cardType == CardType.COOP) {
            CoopCard card = new CoopCard(store, person);
            person.addBonusCard(card);
            return card;
        }
        RimiCard card2 = new RimiCard(store, person);
        person.addBonusCard(card2);
        return card2;
    }

    public abstract BigDecimal collectBonus(double paymentAmount);

    public abstract BigDecimal getBalance();

    public abstract BigDecimal setBalance(BigDecimal some);

    public abstract CardType getCardType();

    public abstract Store getCardStore();

    public abstract Person getCardPerson();

    public BigDecimal useBonus(BigDecimal value) {
        if (getBalance().compareTo(value) < 0) {
            throw new BonusException();
        }
        BigDecimal newBalance = getBalance().subtract(value);
        return setBalance(newBalance);
    }

    public void setBonusBalance(BigDecimal bonusBalance) {
        setBalance(bonusBalance);
    }

    public CardType getType() {
        return getCardType();
    }

    public Store getStore() {
        return getCardStore();
    }

    public BigDecimal getBonusBalance() {
        return getBalance();
    }

    public Person getPerson() {
        return getCardPerson();
    }
}
