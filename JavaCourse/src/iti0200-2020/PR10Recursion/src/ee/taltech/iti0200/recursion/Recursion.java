package ee.taltech.iti0200.recursion;

import ee.taltech.iti0200.recursion.mlp.MyLittlePony;

import java.util.List;

public class Recursion {
    public static List<String> eliminatePonies(List<MyLittlePony> ponies, List<String> ponyNames, Integer listIndex) {
        if (listIndex < ponies.size()) {
            if (listIndex > 0 && ponies.get(listIndex).getPonyType().equals(ponies.get(listIndex - 1).getPonyType())) {
                ponyNames.remove(ponyNames.size() - 1);
            }
            ponyNames.add(ponies.get(listIndex).getName());
            return eliminatePonies(ponies, ponyNames, listIndex + 1);
        }
        return ponyNames;
    }

    public static int getPonyNamesLengthProduct(List<MyLittlePony> ponies, int product) {
        if (ponies.size() > product) {
            return ponies.get(product).getName().length() + getPonyNamesLengthProduct(ponies, product + 1);
        } else return 0;
    }

    public static int getPonyNamesLengthProductExceptType(List<MyLittlePony> ponies,
                                                          int product, MyLittlePony.PonyType type) {
        if (ponies.size() > product) {
            if (ponies.get(product).getPonyType().equals(type)) {
                return getPonyNamesLengthProductExceptType(ponies, product + 1, type);
            } else {
                return ponies.get(product).getName().length() + getPonyNamesLengthProductExceptType(ponies,
                        product + 1, type);
            }
        } else return 0;
    }
}
