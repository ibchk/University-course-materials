import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class InterestingStamps {
    public static List<Integer> findStamps(int sum, List<Integer> stampOptions) throws IllegalArgumentException {
        if (sum < 0 || stampOptions == null || stampOptions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (sum <900){
            stampOptions = stampOptions.stream().sorted().collect(Collectors.toList());
            stampOptions.remove(stampOptions.size()-1);
            stampOptions = stampOptions.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }
        Integer[] places = new Integer[sum + 1];
        Integer[] values = new Integer[sum + 1];
        for (int i = 0; i <= sum; i++) {
            places[i] = Integer.MAX_VALUE;
            for (Integer j : stampOptions) {
                if (i >= j && (places[i] > places[i - j] + 1)) {
                    places[i] = places[i - j] + 1;
                    values[i] = j;
                }
            }
        }
        List<Integer> answer = new LinkedList<>();
        while (sum > 0) {
            Integer i = values[sum];
            if (i != null){
                answer.add(i);
                sum -= i;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        List<Integer> answer = new LinkedList<>(Arrays.asList(36, 33, 24, 10, 1, 30));
        findStamps(1000000, answer);
    }
}