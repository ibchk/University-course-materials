import java.math.BigDecimal;

/**
 * My first algo.
 */
public class AL01A {

    /**
     * magic number.
     */
    final static public int NUMBER3 = 3;

    /**
     * Compute the Fibonacci sequence number.
     *
     * @param n The number of the sequence to compute.
     * @return The n-th number in Fibonacci series.
     */
    public static String iterativeF(int n) {
        if (n == 0) {
            return "0";
        }
        BigDecimal answer = new BigDecimal("1");
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("1");
        for (int i = NUMBER3; i <= n; i++) {
            answer = one.add(two);
            one = two;
            two = answer;
        }
        return answer.toString();
    }
}
