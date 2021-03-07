import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Sum100Test {

    public static final int CALCSUMS12 = 12;
    public static final int NR6 = 6;
    public static final int NR7 = 7;
    public static final int NR8 = 8;
    public static final int NR9 = 9;


    MagicNumber one = new Number((short) 1, false, false);
    MagicNumber two = new Number((short) 2, false, false);
    MagicNumber three = new Number((short) 3, false, false);
    MagicNumber four = new Number((short) 4, false, false);
    MagicNumber five = new Number((short) 5, false, false);
    MagicNumber six = new Number((short) NR6, false, false);
    MagicNumber seven = new Number((short) NR7, false, false);
    MagicNumber eight = new Number((short) NR8, false, false);
    MagicNumber nine = new Number((short) NR9, false, false);

    @org.junit.jupiter.api.Test
    void calcZero() {
        List<MagicNumber> nrs = new LinkedList<>(Arrays.asList(one, two, three, four, five, six, seven, eight, nine));
        assertEquals(0, Sum100.calcSums(nrs).size());
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        one.setCanBeSubtracted(true);
        one.setCanBePlacedNextToOther(true);
        two.setCanBeSubtracted(true);
        two.setCanBePlacedNextToOther(true);
        three.setCanBeSubtracted(true);
        three.setCanBePlacedNextToOther(true);
        four.setCanBeSubtracted(true);
        four.setCanBePlacedNextToOther(true);
        five.setCanBeSubtracted(true);
        five.setCanBePlacedNextToOther(true);
        six.setCanBeSubtracted(true);
        six.setCanBePlacedNextToOther(true);
        seven.setCanBeSubtracted(true);
        seven.setCanBePlacedNextToOther(true);
        eight.setCanBeSubtracted(true);
        eight.setCanBePlacedNextToOther(true);
        nine.setCanBeSubtracted(true);
        nine.setCanBePlacedNextToOther(true);
        List<MagicNumber> nrs = new LinkedList<>(Arrays.asList(one, two, three, four, five, six, seven, eight, nine));
        assertEquals(CALCSUMS12, Sum100.calcSums(nrs).size());
    }
}
