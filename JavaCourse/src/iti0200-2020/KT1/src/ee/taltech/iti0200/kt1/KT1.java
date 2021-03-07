public class KT1 {
    /**
     * Given a non-empty array of integers,
     * every element appears twice except for one. Find that single one.
     * If there are not such (and in any other case) return 0.
     * <p>
     * singleNumber([2,2,1]) => 1
     * singleNumber([4,1,2,1,2]) => 4
     */
    public static int singleNumber(int[] nums) {

        for (int num : nums) {
            int a = num;
            int b = 0;
            for (int num1 : nums) {
                if (num1 == a) {
                    b++;
                }
            }
            if (b == 1) {
                return a;
            }
        }
        return 0;
    }
}
