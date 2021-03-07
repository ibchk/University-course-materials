public class Exam {

    /**
     * Write a recursive method which finds all the double and triple consecutive characters in the string.
     * Triple consecutive characters give 2 points.
     * Double consecutive characters give 1 point,
     * Sums all the combinations and returns the sum.
     * NB! Method must be recursive and not contain any for loops.  Helper functions  and are not allowed.
     * <p>
     * "abc" => 0
     * "aa" => 1
     * "aab" => 1
     * "aaa" => 2
     * "aaabb" => 3
     * "aaaaaa" => 4 (2 times triple 'a', NOT 3 times double 'a')
     * "" => 0
     */
    public static int recCount(String s) {
        int nr = 0;
        if (s.length() > 2) {
            if (s.charAt(0) == s.charAt(1) && s.charAt(2) == s.charAt(0)) {
                nr += 2;
                if (s.length() > 3) {
                    nr += recCount(s.substring(3));
                }
            } else if (s.charAt(0) == s.charAt(1)) {
                nr += 1 + recCount(s.substring(2));
            } else {
                nr += recCount(s.substring(1));
            }
        } else if (s.length() > 1 && s.charAt(0) == s.charAt(1)) {
            nr += 1;
        }
        return nr;
    }
}
