public class Exam {

    /**
    * 01
    * 
     * Write a recursive method which counts how many consecutive characters are "growing":
     * Hint: Chars can be compared just like numbers because each char is represented by number which are also growing
     * in order of alphabet.
     * recGrowth("abc") => 2 (b > a, c > b)
     * recGrowth("acb") => 1 (c > a)
     * recGrowth("aaaa") => 0 (nothing grows)
     * "" => 0
     */
    public static int recGrowth(String text) {
        int answer = 0;
        if (text.length() > 1) {
            if (text.charAt(1) > text.charAt(0)){
                answer += 1;
            }
            answer += recGrowth(text.substring(1));
        }
        return answer;
    }
}
