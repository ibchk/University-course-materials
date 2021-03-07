import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam {
    /**
     * Given lists nums1 and nums2 of the same length,
     * for every element in nums1, consider the corresponding
     * element in nums2 (at the same index).
     * Return the count of the number of times
     * that the two elements differ by 2 or less, but are not equal.
     * <p>
     * matchUp([1, 2, 3], [2, 3, 10]) => 2
     * matchUp([1, 2, 3], [2, 3, 5]) => 3
     * matchUp([1, 2, 3], [2, 3, 3]) => 2
     */
    public static int matchUp(List<Integer> a, List<Integer> b) {
        if (a.size() == 0) {
            return 0;
        }
        int aF = a.get(0);
        int bF = b.get(0);
        if (((aF - bF) < 3 && (aF - bF) > 0) || ((bF - aF) < 3 && (bF - aF) > 0)) {
            if (a.size() == 1) {
                return 1;
            } else return 1 + matchUp(a.subList(1, a.size()), b.subList(1, b.size()));
        } else return matchUp(a.subList(1, a.size()), b.subList(1, b.size()));
    }

    /**
     * Given two strings, word and a separator sep,
     * return a big string made of count occurrences of the word,
     * separated by the separator string.
     * <p>
     * repeatSeparator("Word", "X", 3) => "WordXWordXWord"
     * repeatSeparator("This", "And", 2) => "ThisAndThis"
     * repeatSeparator("This", "And", 1) => "This"
     */
    public static String repeatSeparator(String word, String sep, int count) {
        StringBuilder finalAnswer = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                finalAnswer.append(sep);
            }
            finalAnswer.append(word);
        }
        return finalAnswer.toString();
    }
}