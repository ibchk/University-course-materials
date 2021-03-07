package ee.taltech.iti0200.datastructures;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DataStructures {

    /**
     * Given String is a sentence with some words.
     * There are only single whitespace between every word and no punctuation marks.
     * Also there are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there are more than one word with the same length then return the word which comes alphabetically first.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can definitely help
     * you to find the word which comes alphabetically first.
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        String longestWord = "";
        for (String word : sentence.split(" ")) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            } else if (word.length() == longestWord.length()) {
                String[] arrayOfTwoStrings = {longestWord, word};
                Arrays.sort(arrayOfTwoStrings);
                longestWord = arrayOfTwoStrings[0];
            }
        }
        return longestWord;
    }

    /**
     * Imagine yourself as a librarian who needs to rearrange a bookshelf in the library.
     * <p>
     * You have an input List with book name and author.(in format [Name-Author])
     * If the first letter of the book name and the author name are the same (case-insensitive)
     * then you need to put the book (name) on left part of the shelf (before every other book),
     * if not then on the right part (after every other book).
     * The list will contain book names (and a separator).
     * <p>
     * <p>
     * Shelf separator is "][". It will be a separate element in your list.
     * There is always exactly one separator in your result list.
     * So if the bookshelf is empty it should look like [][] when printing out.
     * If there is one book on the left side then: [Raamat, ][]
     * If there are books on both sides then: [LeftBook, ][, RightBook]
     * <p>
     * To add smth to the beginning of the list or to the end take a look at LinkedList data structure.
     * For example list.addFirst() can help you.
     * <p>
     * There are some examples below.
     * <p>
     * PS. BE CAREFUL WITH CAPITAL AND NON-CAPITAL LETTERS!
     *
     * @param books input List to locate correctly.
     * @return List of book names located correctly
     */
    public static List<String> rearrangeTheShelf(List<String> books) {
        LinkedList<String> listOfBooks = new LinkedList<String>();
        listOfBooks.add("][");
        for (String book : books) {
            String[] newArray = book.split("-");
            if (newArray[1].toLowerCase().charAt(0) == newArray[0].toLowerCase().charAt(0)) {
                listOfBooks.addFirst(newArray[0]);
            } else {
                listOfBooks.addLast(newArray[0]);
            }
        }
        return listOfBooks;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        LinkedList<String> listOfWords = new LinkedList<String>();
        Map<String, Integer> mapOfWords = new HashMap<>();
        for (String word : words) {
            if (mapOfWords.containsKey(word)) {
                listOfWords.add(word);
                mapOfWords.remove(word);
            } else {
                mapOfWords.put(word, 1);
            }
        }
        return listOfWords;
    }


    public static void main(String[] args) {
        System.out.println(onlyEvenWords(Arrays.asList("foo", "bar", "baz", "baz", "bar", "foo"))); // [baz, bar, foo]
        System.out.println(onlyEvenWords(Arrays.asList("a", "b", "b", "a"))); // [b, a]
        System.out.println(onlyEvenWords(Arrays.asList("eggs", "bacon", "SPAM", "ham", "SPAM", "SPAM"))); // [SPAM]
    }
}
