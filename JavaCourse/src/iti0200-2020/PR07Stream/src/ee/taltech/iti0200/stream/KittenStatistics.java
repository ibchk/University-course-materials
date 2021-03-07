import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    public OptionalDouble findKittensAverageAge() {
        if (kittens.size() > 0) {
            return kittens.stream()
                    .mapToInt(Kitten::getAge)
                    .average();
        }
        return OptionalDouble.empty();
    }

    public Optional<Kitten> findOldestKitten() {
        if (kittens.size() > 0) {
            return kittens.stream().max(Comparator.comparingInt(Kitten::getAge));
        }
        return Optional.empty();
    }

    public List<Kitten> findYoungestKittens() {
        List<Kitten> kittenList = new ArrayList<>();
        if (kittens.size() > 0) {
            return kittens.stream()
                    .filter(kitten -> kitten.getAge() == kittens
                            .stream()
                            .min(Comparator.comparingInt(Kitten::getAge))
                            .get()
                            .getAge())
                    .collect(Collectors.toList());
        }
        return kittenList;
    }

    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        return kittens.stream().filter(kitten -> kitten.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        return kittens
                .stream()
                .filter(kitten -> kitten
                        .getAge() >= minAge && kitten.getAge() <= maxAge)
                .collect(Collectors.toList());

    }

    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        return kittens
                .stream()
                .filter(kitten -> kitten.getName()
                        .toLowerCase()
                        .equals(givenName
                                .toLowerCase()))
                .findFirst();
    }

    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream().sorted(Comparator.comparingInt(Kitten::getAge)).collect(Collectors.toList());
    }

    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream().sorted(Comparator.comparingInt(Kitten::getAge).reversed()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Kitten kitten1 = new Kitten("kitten1", Kitten.Gender.FEMALE, 100);
    }
}
