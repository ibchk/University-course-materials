package ee.taltech.iti0200.bonuscards;

import ee.taltech.iti0200.bonuscards.cards.BonusCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Store {
    private Set<Person> customers = new HashSet<>();
    String name;

    public Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Person> getCustomers() {
        return customers;
    }

    public void addCustomer(Person person) {
        customers.add(person);
    }

    public void removeCustomer(Person person) {
        customers.remove(person);
    }

    /**
     * Gets customer with the highest bonus balance.
     *
     * @param cardType the bonus card type
     * @return customer with the highest bonus balance
     */
    public Optional<Person> getCustomerWithHighestBonusBalance(BonusCard.CardType cardType) {
        Person customer = null;
        for (Person person : customers) {
            if (person.getBonusCardByType(cardType).isPresent()) {
                if (customer == null) {
                    customer = person;
                } else {
                    int comarison = person.getBonusCardByType(cardType).get()
                            .getBonusBalance().compareTo(customer.getBonusCardByType(cardType).get().getBonusBalance());
                    if (comarison > 0) {
                        customer = person;
                    }
                }
            }
        }
        return (customer == null) ? Optional.empty() : Optional.of(customer);
    }

    /**
     * Gets customer with the lowest bonus balance who is younger than the specified age.
     *
     * @param cardType the bonus card type
     * @param age      the age
     * @return customer
     */
    public Optional<Person> getCustomerWithLowestBonusBalanceYoungerThan(BonusCard.CardType cardType, int age) {
        Person customer = null;
        for (Person person : customers) {
            if (person.getBonusCardByType(cardType).isPresent() && person.getAge() < age && customer == null) {
                customer = person;
            } else if (person.getBonusCardByType(cardType).isPresent() && person.getAge() < age) {
                int comarison = person.getBonusCardByType(cardType).get().getBonusBalance()
                        .compareTo(customer.getBonusCardByType(cardType).get().getBonusBalance());
                if (comarison < 0) {
                    customer = person;
                }
            }
        }
        return (customer == null) ? Optional.empty() : Optional.of(customer);
    }

    /**
     * Gets total bonuses.
     *
     * @param cardType the bonus card type
     * @return the total bonuses
     */
    public BigDecimal getTotalBonuses(BonusCard.CardType cardType) {
        BigDecimal bonuses = new BigDecimal(0);
        for (Person person : customers) {
            if (person.getBonusCardByType(cardType).isPresent()) {
                bonuses = bonuses.add(person.getBonusCardByType(cardType).get().getBonusBalance());
            }
        }
        return bonuses;
    }

    /**
     * Gets average bonus.
     *
     * @param cardType the bonus card type
     * @return the average bonus
     */
    public BigDecimal getAverageBonus(BonusCard.CardType cardType) {
        BigDecimal bonuses = new BigDecimal(0);
        for (Person person : customers) {
            if (person.getBonusCardByType(cardType).isPresent()) {
                bonuses = bonuses.add(person.getBonusCardByType(cardType).get().getBonusBalance());
            }
        }
        return (bonuses.compareTo(new BigDecimal(0)) == 0)
                ? new BigDecimal(0) : bonuses.divide(new BigDecimal(customers.size()), RoundingMode.UNNECESSARY);
    }
}
