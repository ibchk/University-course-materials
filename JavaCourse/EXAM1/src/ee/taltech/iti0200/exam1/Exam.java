import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Exam {

    /**
     * 01
     * <p>
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     * <p>
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        int nr = 1;
        List<Integer> newNums = new LinkedList<>();
        for (Integer num : nums) {
            if (num % 10 == 0) {
                nr = num;
            }
            if (nr != 1) {
                newNums.add(nr);
            } else newNums.add(num);
        }
        return newNums;
    }


    /**
     * 02
     * <p>
     * Write a method that analyzes input String and returns all pairs of same letter that is present as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order. If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return 0.
     * Take latin alpahbet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     *
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder answer = new StringBuilder();
        for (int n = 0; n < alphabet.length(); n++) {
            boolean small = false;
            boolean big = false;
            char letter = alphabet.charAt(n);
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == letter) {
                    small = true;
                } else if (input.charAt(j) == Character.toUpperCase(letter)) {
                    big = true;
                }
            }
            if (small && big) {
                answer.append(Character.toUpperCase(letter));
                answer.append(letter);
            }
        }
        return answer.toString();
    }


    /**
     * 03
     * <p>
     * Write a recursive method (no loops, no global variables, calls itself)
     * which separates different letters by single space.
     * <p>
     * recSeparate("abc") => "a b c"
     * recSeparate("aabbc") => aa bb c"
     * recSeparate("aaaabbbccd") => "aaaa bbb cc d"
     * recSeparate("") => ""
     * recSeparate("aaa") => "aaa"
     *
     * @param text
     * @return
     */
    public static String recSeparate(String text) {
        String answer = "";
        if (text.length() > 1) {
            String letter = recSeparate(text.substring(1));
            if (text.charAt(0) == letter.charAt(0)) {
                answer += text.charAt(0);
                answer += letter;
            } else {
                answer += text.charAt(0);
                answer += " ";
                answer += letter;
            }
        } else if (text.length() > 0) {
            answer += text.charAt(0) + "";
        }
        return answer;
    }
}
