package ee.taltech.iti0200.bonuscards.cards;

import ee.taltech.iti0200.bonuscards.Person;
import ee.taltech.iti0200.bonuscards.Store;
import ee.taltech.iti0200.bonuscards.exceptions.AlreadyExistingCardTypeException;
import ee.taltech.iti0200.bonuscards.exceptions.BonusException;
import ee.taltech.iti0200.bonuscards.exceptions.PersonException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusCardTest {

    public static final int AGE25 = 25;
    public static final int AGE23 = 23;
    public static final int AGE10 = 10;
    public static final int AMOUNT2 = 2;
    public static final int AMOUNT1 = 1;
    public static final int AMOUNT0 = 0;
    public static final int BONUSAMOUNT100 = 100;
    public static final int BONUSAMOUNT90 = 90;
    public static final int AGE20 = 20;
    public static final int BONUSAMOUNT10 = 10;
    public static final int BONUSAMOUNT12 = 12;
    public static final int BONUSAMOUNT42 = 42;
    public static final int AGE0 = 0;
    public static final int AGE50 = 50;
    public static final int BONUSAMOUNT110 = 110;
    public static final int BONUSAMOUNT55 = 55;
    public static final int BONUSAMOUNT37 = 37;
    public static final int BONUSAMOUNT9 = 9;

    Store coop = new Store("Coop");
    Store rimi = new Store("Rimi");
    Person kalle = new Person("Kalle", "Kuusk", AGE10, Person.Gender.MALE);
    Person malle = new Person("Malle", "Mänd", AGE25, Person.Gender.MALE);
    BonusCard malleRimi = BonusCard.createCard(BonusCard.CardType.RIMI, rimi, malle);
    BonusCard kalleRimi = BonusCard.createCard(BonusCard.CardType.RIMI, rimi, kalle);
    BonusCard kalleCoop = BonusCard.createCard(BonusCard.CardType.COOP, coop, kalle);
    BonusCard malleCoop = BonusCard.createCard(BonusCard.CardType.COOP, coop, malle);

    @Test
    void createCard() {
        try {
            BonusCard kalleRimi = BonusCard.createCard(BonusCard.CardType.RIMI, rimi, kalle);
        } catch (AlreadyExistingCardTypeException ignored) {
        }
        assertEquals(rimi, kalleRimi.getStore());
        assertEquals(coop, malleCoop.getStore());
        assertEquals(new BigDecimal("10"), kalleCoop.getBonusBalance());
        assertEquals(new BigDecimal("0"), kalleRimi.getBonusBalance());
        assertEquals(BonusCard.CardType.COOP, kalleCoop.getType());
        assertEquals(BonusCard.CardType.RIMI, kalleRimi.getType());
        assertEquals(kalle, kalleRimi.getPerson());
        assertEquals(kalle, kalleCoop.getPerson());
        assertEquals(malle, malleRimi.getPerson());
        assertEquals(malle, malleCoop.getPerson());
        assertEquals(AMOUNT2, kalle.getBonusCards().size());
        kalle.removeBonusCard(kalleCoop);
        assertEquals(AMOUNT1, kalle.getBonusCards().size());
        kalle.removeBonusCard(kalleRimi);
        assertEquals(AMOUNT0, kalle.getBonusCards().size());
        Set<BonusCard> cardsSet = new HashSet<>();
        cardsSet.add(kalleCoop);
        cardsSet.add(kalleRimi);
        Person kalleNew = new Person("Kalle", "Kuusk", AGE25, Person.Gender.MALE, cardsSet);
        assertEquals(AMOUNT2, kalleNew.getBonusCards().size());
    }

    @Test
    void collectBonus() {
        kalleRimi.collectBonus(BONUSAMOUNT100);
        malleCoop.collectBonus(BONUSAMOUNT100);
        assertEquals(new BigDecimal("15.0"), malleCoop.getBonusBalance());
        assertEquals(new BigDecimal("2.000"), kalleRimi.getBonusBalance());
    }

    @Test
    void useBonus() {
        kalleRimi.collectBonus(BONUSAMOUNT100);
        malleCoop.collectBonus(BONUSAMOUNT100);
        try {
            kalleRimi.useBonus(new BigDecimal(BONUSAMOUNT10));
        } catch (BonusException ignored) {
        }
        kalleRimi.setBonusBalance(new BigDecimal(BONUSAMOUNT12));
        kalleRimi.useBonus(new BigDecimal(BONUSAMOUNT10));
        assertEquals(new BigDecimal("2"), kalleRimi.getBonusBalance());
    }

    @Test
    void setBonusBalance() {
        kalleRimi.setBonusBalance(new BigDecimal(BONUSAMOUNT100));
        kalleRimi.setBonusBalance(new BigDecimal(BONUSAMOUNT42));
        assertEquals(new BigDecimal("42"), kalleRimi.getBonusBalance());
    }

    @Test
    void checkStore() {
        assertEquals("Coop", coop.getName());
        Set<Person> customersSet = new HashSet<>();
        customersSet.add(kalle);
        customersSet.add(malle);
        assertEquals(customersSet, coop.getCustomers());
        assertEquals(customersSet, rimi.getCustomers());
        kalleCoop.setBonusBalance(new BigDecimal(BONUSAMOUNT100));
        kalleRimi.setBonusBalance(new BigDecimal(BONUSAMOUNT90));
        assertEquals(new BigDecimal(BONUSAMOUNT110), coop.getTotalBonuses(BonusCard.CardType.COOP));
        assertEquals(new BigDecimal(BONUSAMOUNT55), coop.getAverageBonus(BonusCard.CardType.COOP));
        Person mallePoor = new Person("Malle", "Mänd", AGE23, Person.Gender.MALE);
        BonusCard mallePoorCoop = BonusCard.createCard(BonusCard.CardType.COOP, coop, mallePoor);
        mallePoorCoop.useBonus(new BigDecimal(BONUSAMOUNT9));
        assertEquals(new BigDecimal(BONUSAMOUNT37), coop.getAverageBonus(BonusCard.CardType.COOP));
        assertEquals(Optional.of(kalle), coop.getCustomerWithHighestBonusBalance(BonusCard.CardType.COOP));
        assertEquals(Optional.of(kalle), rimi.getCustomerWithHighestBonusBalance(BonusCard.CardType.RIMI));
        malleCoop.setBonusBalance(new BigDecimal("0"));
        assertEquals(Optional.of(malle),
                coop.getCustomerWithLowestBonusBalanceYoungerThan(BonusCard.CardType.COOP, AGE50));
        assertEquals(Optional.of(kalle),
                rimi.getCustomerWithLowestBonusBalanceYoungerThan(BonusCard.CardType.RIMI, AGE25));

        Store coop2 = new Store("Coop");
        Person kalle2 = new Person("Kalle", "Kuusk", AGE25, Person.Gender.MALE);
        Person malle2 = new Person("Malle", "Mänd", 2, Person.Gender.MALE);
        BonusCard kalleCoop2 = BonusCard.createCard(BonusCard.CardType.COOP, coop2, kalle2);
        BonusCard malleCoop2 = BonusCard.createCard(BonusCard.CardType.COOP, coop2, malle2);
        coop2.removeCustomer(malle2);
        assertEquals(AMOUNT1, coop2.getCustomers().size());
        coop2.removeCustomer(kalle2);
        assertEquals(Optional.empty(), coop2.getCustomerWithHighestBonusBalance(BonusCard.CardType.COOP));
    }

    @Test
    void checkPerson() {
        try {
            Person malle = new Person("Malle", "Mänd", AGE0, Person.Gender.MALE);
        } catch (PersonException ignored) {
        }
        Person sally = new Person("Sally", "Iva", AGE20, Person.Gender.FEMALE);
        assertEquals("Sally", sally.getFirstName());
        assertEquals("Iva", sally.getLastName());
        assertEquals(AGE20, sally.getAge());
        assertEquals(Person.Gender.FEMALE, sally.getGender());
    }
}
