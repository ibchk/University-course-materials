package ee.taltech.iti0200.introduction;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Introduction {


    /**
     * Method gets a string that contains x words.
     * The first character of the string starts a new word, next words always start with a capital letter.
     * Words are not separated by whitespace.
     * Words (including the first character) can contain all kinds of symbols.
     * The function should find and return x.
     *
     * @param string Given string that contains x words.
     * @return The number of words in the given string.
     */
    public int camelCaseWordCounter(String string) {
        int nr = 0;
        for (int i = 0; i < string.length(); i++) {
            if (i == 0 || Character.isLetter(string.charAt(i)) && Character.isUpperCase(string.charAt(i))) {
                nr++;
            }
        }
        return nr;
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {
        List<Integer> li = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                li.add(number);
            }
        }
        return li;
    }

    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     * <p>
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        List<Integer> li = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                li.add(number);
            }
        }
        int[] lst = new int[li.size()];
        for (int i = 0; i < li.size(); i++) {
            lst[i] = li.get(i);
        }
        return lst;
    }

    public static void main(String[] args) {
        Introduction introduction = new Introduction();
        System.out.println(introduction.camelCaseWordCounter("AAAAA")); // 3

        List<Integer> nums = new ArrayList<>(Arrays.asList(9, 0, 24, -6, 3));
        System.out.println(introduction.findEvenNumbersList(nums)); // [4, 2, 2, -2, 0]

        int[] array = {9, 0, 24, -6, 3};
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array))); // [0, 24, -6]
    }
}

