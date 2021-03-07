import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Sum100 {

    public static final int ENDINDEX = 9;
    public static final int TWONRSENDINDEX = 8;
    public static final int THREENRSENDINDEX = 7;


    public static List<String> calcSums(List<MagicNumber> input) {
        return calcSums(input, 0, "", 0);
    }

    public static List<String> calcSums(List<MagicNumber> input, int index, String sum, int checkSum) {
        if (index == ENDINDEX && checkSum == 100) {
            return new LinkedList<String>(Collections.singletonList(sum));
        } else if (index == ENDINDEX) {
            return new LinkedList<>();
        } else {
            List<String> listToReturn = new LinkedList<>();
            if (index != 0) {
                listToReturn.addAll(calcSums(input, index + 1, sum + "+" + input.get(index).getNumber(),
                        checkSum + input.get(index).getNumber()));
            } else {
                listToReturn.addAll(calcSums(input, index + 1, sum + input.get(index).getNumber(),
                        checkSum + input.get(index).getNumber()));
            }
            if (input.get(index).canBeSubtracted()) {
                listToReturn.addAll(calcSums(input, index + 1, sum + "-" + input.get(index).getNumber(),
                        checkSum - input.get(index).getNumber()));
            }
            listToReturn.addAll(twoNumsTogether(input, index, sum, checkSum));
            listToReturn.addAll(threeNumsTogether(input, index, sum, checkSum));
            return listToReturn;
        }
    }

    public static List<String> twoNumsTogether(List<MagicNumber> input, int index, String sum, int checkSum) {
        List<String> listToReturn = new LinkedList<>();
        if (input.get(index).canBePlacedNextToOther() && index < TWONRSENDINDEX && input.get(index + 1)
                .canBePlacedNextToOther()) {
            if (index != 0) {
                listToReturn.addAll(calcSums(input, index + 2, sum + "+" + input.get(index).getNumber()
                        + input.get(index + 1).getNumber(), checkSum + Integer.parseInt(String
                        .valueOf(input.get(index).getNumber()) + String.valueOf(input.get(index + 1).getNumber()))));
            } else {
                listToReturn.addAll(calcSums(input, index + 2, sum + input.get(index).getNumber()
                        + input.get(index + 1).getNumber(), checkSum + Integer.parseInt(String.
                        valueOf(input.get(index).getNumber()) + String.valueOf(input.get(index + 1).getNumber()))));
            }
            if (input.get(index).canBeSubtracted() && input.get(index + 1).canBeSubtracted()) {
                listToReturn.addAll(calcSums(input, index + 2, sum + "-" + input.get(index).getNumber()
                        + input.get(index + 1).getNumber(), checkSum - Integer.parseInt(String
                        .valueOf(input.get(index).getNumber()) + String.valueOf(input.get(index + 1).getNumber()))));
            }
        }
        return listToReturn;
    }

    public static List<String> threeNumsTogether(List<MagicNumber> input, int index, String sum, int checkSum) {
        List<String> listToReturn = new LinkedList<>();
        if (input.get(index).canBePlacedNextToOther() && index < THREENRSENDINDEX && input.get(index + 1)
                .canBePlacedNextToOther()
                && input.get(index + 2).canBePlacedNextToOther()) {
            if (index != 0) {
                listToReturn.addAll(calcSums(input, index + 3, sum + "+" + input.get(index).getNumber()
                        + input.get(index + 1).getNumber() + input.get(index + 2).getNumber(), checkSum
                        + Integer.parseInt(String.valueOf(input.get(index).getNumber()) + String.valueOf(input
                        .get(index + 1).getNumber()) + String.valueOf(input.get(index + 1).getNumber()))));
            } else {
                listToReturn.addAll(calcSums(input, index + 3, sum + input.get(index).getNumber()
                        + input.get(index + 1).getNumber() + input.get(index + 2).getNumber(), checkSum
                        + Integer.parseInt(String.valueOf(input.get(index).getNumber()) + String.valueOf(input
                        .get(index + 1).getNumber()) + String.valueOf(input.get(index + 2).getNumber()))));
            }
            if (input.get(index).canBeSubtracted() && input.get(index + 1).canBeSubtracted()
                    && input.get(index + 2).canBeSubtracted()) {
                listToReturn.addAll(calcSums(input, index + 3, sum + "-" + input.get(index).getNumber()
                        + input.get(index + 1).getNumber() + input.get(index + 2).getNumber(), checkSum
                        - Integer.parseInt(String.valueOf(input.get(index).getNumber()) + String.valueOf(input
                        .get(index + 1).getNumber()) + String.valueOf(input.get(index + 1).getNumber()))));
            }
        }
        return listToReturn;
    }
}
