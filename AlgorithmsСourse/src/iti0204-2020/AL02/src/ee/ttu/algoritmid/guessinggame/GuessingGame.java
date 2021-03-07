import jdk.jfr.Frequency;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GuessingGame {

    Oracle oracle;

    public GuessingGame(Oracle oracle) {
        this.oracle = oracle;
    }

    /**
     * @param fruitArray - All the possible fruits.
     * @return the name of the fruit.
     */
    public String play(Fruit[] fruitArray) {
        List<Fruit> sortedList = Arrays.stream(fruitArray).sorted(Comparator.comparingInt(Fruit::getWeight)).collect(Collectors.toList());
        Integer first = 0;
        Integer last = sortedList.size() - 1;
        while (true) {
            String answer = oracle.isIt(sortedList.get((last + first) / 2));
            System.out.println(Math.ceil((last + first) / 2) + " " + first + last);
            if (answer.equals("heavier")) {
                first = (last + first) / 2;
                if (last - first == 1) {
                    return sortedList.get(last).getName();
                }
            } else if (answer.equals("lighter")) {
                last = (last + first) / 2;
            } else {
                break;
            }
        }
        return sortedList.get((last + first) / 2).getName();
    }

    public static void main(String[] args) {
//        ExampleTest test = new ExampleTest();

        Fruit[] array = {new Fruit("Apelsin", 150),
                new Fruit("Banaan", 185),
                new Fruit("Greip", 250),
                new Fruit("Mango", 210),
                new Fruit("Pirn", 170),
                new Fruit("Ploom", 50),
                new Fruit("Virsik", 130),
                new Fruit("Õun", 110)
        };
        final Fruit correct = array[2];
        Oracle oracle = new Oracle(correct);
        GuessingGame nm = new GuessingGame(oracle);
        System.out.println(nm.play(array));
    }
}