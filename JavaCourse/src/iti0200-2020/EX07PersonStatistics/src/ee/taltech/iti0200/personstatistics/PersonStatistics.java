package ee.taltech.iti0200.personstatistics;

import java.util.Optional;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonStatistics {
    private List<Person> persons;

    public PersonStatistics(List<Person> persons) {
        this.persons = persons;
    }

    public long countPersons() {
        return persons.size();
    }

    public OptionalDouble findAverageHeight() {
        if (persons.size() > 0) {
            return persons.stream()
                    .mapToDouble(Person::getHeightInMeters)
                    .average();
        }
        return OptionalDouble.empty();
    }

    public Optional<Person> findYoungestPerson() {
        if (persons.size() > 0) {
            return persons
                    .stream()
                    .min(Comparator
                            .comparingInt(Person::getAge));
        }
        return Optional.empty();
    }

    public Optional<Person> findOldestPerson() {
        if (persons.size() > 0) {
            return persons
                    .stream()
                    .max(Comparator
                            .comparingInt(Person::getAge));
        }
        return Optional.empty();
    }

    public Optional<String> findLongestLastName() {
        if (persons.size() > 0) {
            return Optional.of(persons
                    .stream()
                    .max(Comparator
                            .comparing(person -> person
                                    .getLastName()
                                    .length()))
                    .get()
                    .getLastName());
        }
        return Optional.empty();
    }

    public List<String> getNationalityData() {
        return persons
                .stream()
                .map(Person::getNationality)
                .collect(Collectors
                        .toList());
    }

    /**
     * Converts persons heights from m to cm.
     *
     * @return list of heights in cm
     */
    public List<Double> getHeightInCm() {
        return persons
                .stream()
                .map(person -> person
                        .getHeightInMeters() * 100)
                .collect(Collectors
                        .toList());
    }

    public List<Person> findSamples(int sampleSize) {
        if (sampleSize < 0) {
            return new LinkedList<>();
        }
        return persons
                .stream()
                .limit(sampleSize)
                .collect(Collectors
                        .toList());
    }

    /**
     * Find first person matching provided parameters criterias.
     *
     * @return first matching person
     */
    public Optional<Person> findSamplePerson(String nationality, Gender gender, int age) {
        return persons
                .stream()
                .filter(person -> person
                        .getGender()
                        .equals(gender) && person
                        .getNationality()
                        .equals(nationality) && person
                        .getAge() == age)
                .findFirst();
    }

    public Set<String> getDistinctFirstNames() {
        return persons
                .stream()
                .map(Person::getFirstName)
                .collect(Collectors
                        .toSet());
    }

    /**
     * Order persons from tallest to shortest.
     *
     * @return ordered list of persons
     */
    public List<Person> getReverseOrderedByHeight() {
        return persons
                .stream()
                .sorted(Comparator
                        .comparingDouble(Person::getHeightInMeters)
                        .reversed())
                .collect(Collectors
                        .toList());
    }

    public List<Person> findBetweenAge(int ageFrom, int ageTo) {
        return persons
                .stream()
                .filter(person -> person
                        .getAge() >= ageFrom && person
                        .getAge() <= ageTo)
                .collect(Collectors
                        .toList());
    }

    /**
     * Find persons whose first name first letter is same as his/her nationality first letter.
     *
     * @return list of matching persons
     */
    public List<Person> findSameLetterNameAndNationality() {
        return persons.stream()
                .filter(person -> person.getNationality()
                        .charAt(0) == person
                        .getFirstName()
                        .charAt(0))
                .collect(Collectors
                        .toList());
    }

    /**
     * Create map where each occupation has list of persons who have that occupation.
     *
     * @return map of occupations with persons
     */
    public Map<String, List<Person>> mapOccupationToPersons() {
        return persons.stream()
                .collect(Collectors
                        .toMap(Person::getOccupation, person -> persons
                                        .stream()
                                        .filter(person1 -> person1
                                                .getOccupation()
                                                .equals(person
                                                        .getOccupation()))
                                        .collect(Collectors
                                                .toList()),
                                (people, people2) -> people));
    }
}
