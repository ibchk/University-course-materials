import java.util.List;
import java.util.Map;

public class Exam {

    /**
     * Return the sum of the numbers in the array,
     * except ignore sections of numbers
     * starting with a 6 and extending to the next 7
     * (every 6 will be followed by at least one 7).
     * Return 0 for no numbers.
     * <p>
     * sum67([1, 2, 2]) => 5
     * sum67([1, 2, 2, 6, 99, 99, 7]) => 5
     * sum67([1, 1, 6, 7, 2]) => 4
     */
    public static int sum67(List<Integer> numbers) {
        int sum = 0;
        boolean toAdd = true;
        for (Integer number : numbers) {
            if (number.equals(6)) {
                toAdd = false;
            } else if (number.equals(7) && !toAdd) {
                toAdd = true;
            } else if (toAdd) {
                sum += number;
            }
        }
        return sum;
    }

    /**
     * Given a string, compute a new string by moving the first char to come after
     * the next two chars, so "abc" yields "bca".
     * Repeat this process for each subsequent group of 3 chars, so "abcdef" yields
     * "bcaefd". Ignore any group of fewer than 3 chars at the end.
     * <p>
     * oneTwo("abc") => "bca"
     * oneTwo("tca") => "cat"
     * oneTwo("tcagdo") => "catdog"
     */
    public static String oneTwo(String str) {
        StringBuilder newStr = new StringBuilder();
        while (str.length() > 2) {
            newStr.append(str.substring(1, 3)).append(str.substring(0, 1));
            if (str.length() > 3) {
                str = str.substring(3);

            } else {
                str = "";
            }
        }
        return newStr.toString();
    }

    /**
     * Modify and return the given map as follows:
     * if exactly one of the keys "a" or "b" exists in the map (but not both), set the other
     * to have that same value in the map.
     * <p>
     * mapAXorB({"a": "aaa", "c": "cake"}) => {"a": "aaa", "b": "aaa", "c": "cake"}
     * mapAXorB({"b": "bbb", "c": "cake"}) => {"a": "bbb", "b": "bbb", "c": "cake"}
     * mapAXorB({"a": "aaa", "b": "bbb", "c": "cake"}) => {"a": "aaa", "b": "bbb", "c": "cake"}
     */
    public static Map<String, String> mapAXorB(Map<String, String> map) {
        if (map.containsKey("a") && !map.containsKey("b")) {
            map.put("b", map.get("a"));
        } else if (!map.containsKey("a") && map.containsKey("b")) {
            map.put("a", map.get("b"));
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(oneTwo(""));
    }
}
