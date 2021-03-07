import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class AL01B {


    /**
     * Estimate or find the exact time required to compute the n-th Fibonacci number.
     *
     * @param n The n-th number to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public static String timeToComputeRecursiveFibonacci(int n) {
        BigDecimal time;
        if (n < 44) {
            BigDecimal startTime = new BigDecimal(System.nanoTime());
            recursiveF(n);
            time = new BigDecimal(System.nanoTime()).subtract(startTime)
                    .divide(new BigDecimal("31536000000000000"), 10, RoundingMode.CEILING);
        } else {
            BigDecimal timeSum = new BigDecimal(0);
            for (int i = 0; i < 2000; i++) {
                BigDecimal startTime = new BigDecimal(System.nanoTime());
                recursiveF(20);
                timeSum = timeSum.add(new BigDecimal(String.valueOf(new BigDecimal(System.nanoTime())
                        .subtract(startTime))));
            }
            time = timeSum.multiply(lineReader(n).divide(new BigDecimal(40586000), RoundingMode.CEILING))
                    .divide(new BigDecimal("31536000000000000"), 10, RoundingMode.CEILING);
        }
        return time.toString();
    }

    public static BigDecimal lineReader(int n) {
        BigDecimal lines = new BigDecimal("1");
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("1");
        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                lines = new BigDecimal(String.valueOf(one.add(two).add(new BigDecimal("2"))));
                one = two;
                two = lines;
            }
        }
        return lines;
    }

    /**
     * Compute the Fibonacci sequence number recursively.
     * (You need this in the timeToComputeRecursiveFibonacci(int n) function!)
     *
     * @param n The n-th number to compute.
     * @return The n-th Fibonacci number as a string.
     */
    public static BigInteger recursiveF(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);
        return recursiveF(n - 1).add(recursiveF(n - 2));
    }

    public static void main(String[] args) {
        System.out.println(timeToComputeRecursiveFibonacci(150));
    }
}