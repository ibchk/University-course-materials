package ee.taltech.iti0200.bonuscards;

import ee.taltech.iti0200.bonuscards.cards.BonusCard;
import ee.taltech.iti0200.bonuscards.exceptions.PersonException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Person {
    String firstName;
    String lastName;
    int age;
    Gender gender;
    private Set<BonusCard> bonusCards = new HashSet<>();

    public enum Gender { MALE, FEMALE }

    public Person(String firstName, String lastName, int age, Gender gender) {
        if (age < 1) {
            throw new PersonException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public Person(String firstName, String lastName, int age, Gender gender, Set<BonusCard> bonusCards) {
        this.bonusCards = bonusCards;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<BonusCard> getBonusCards() {
        return bonusCards;
    }

    /**
     * Gets bonus card by the specified type.
     *
     * @param cardType the bonus card type
     * @return bonus card with the specified type
     */
    public Optional<BonusCard> getBonusCardByType(BonusCard.CardType cardType) {
        for (BonusCard card : bonusCards) {
            if (card.getType().equals(cardType)) {
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }

    public void addBonusCard(BonusCard bonusCard) {
        bonusCards.add(bonusCard);
    }

    public void removeBonusCard(BonusCard bonusCard) {
        bonusCards.remove(bonusCard);
    }
}
