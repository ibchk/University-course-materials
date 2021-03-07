package ee.taltech.iti0200.datatypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataTypes {

    /**
     * Find all elements that appear more than once over all lists.
     * ["this", "is", "fun", "this", "is", "rad"], ["actually", "it", "is", "not", "fun"] ===>
     * ====> ["this", "is", "fun"]
     *
     * @param data List of lists containing strings
     * @return Set of unique data
     */
    public static Set<String> getUniqueDuplicates(List<List<String>> data) {
        Map<String, Integer> mapOfWords = new HashMap<>();
        Set<String> duplicates = new HashSet<>();
        for (List list : data) {
            for (Object oneWord : list) {
                String stringWord = oneWord.toString();
                if (mapOfWords.containsKey(stringWord)) {
                    mapOfWords.put(stringWord, mapOfWords.get(stringWord) + 1);
                    if (mapOfWords.get(stringWord) == 2) {
                        duplicates.add(stringWord);
                    }
                } else {
                    mapOfWords.put(oneWord.toString(), 1);
                }
            }
        }
        return duplicates;
    }

    private static BigInteger factorial(int n) {
        if (n < 1) {
            return new BigInteger("1");
        }
        BigInteger finalBigInteger = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            finalBigInteger = finalBigInteger.multiply(new BigInteger(Integer.toString(i)));
        }
        return finalBigInteger;
    }

    /**
     * Calculate B^P mod M using BigInteger data type.
     * <p>
     * B = b!, P = p!, M = m!
     * n! = 1 * 2 * ... * (n-2) * (n-1) * n
     *
     * @return Algorithm value using BigInteger
     */
    public static BigInteger bigMod(int b, int p, int m) {
        BigInteger newB = factorial(b);
        BigInteger newP = factorial(p);
        BigInteger newM = factorial(m);
        return newB.modPow(newP, newM);
    }

    /**
     * Convert different currencies to euros and return the sum it.
     * <p>
     * Map of CurrencyToEurRate always contains necessary exchange rates.
     *
     * @param data              Map containing the name of currency and a list of different values
     * @param currencyToEurRate Map containing the name of currency and its exchange rate to euro
     * @return Sum of money in euros
     */
    public static BigDecimal currencyConverter(Map<String, List<BigDecimal>> data, Map<String,
            BigDecimal> currencyToEurRate) {
        BigDecimal eur = new BigDecimal(0);
        if (data.isEmpty() || currencyToEurRate.isEmpty()) {
            return eur;
        }
        for (String currency : currencyToEurRate.keySet()) {
            for (String moneySymbol : data.keySet()) {
                if (currency.toLowerCase().equals(moneySymbol.toLowerCase())) {
                    for (BigDecimal money : data.get(moneySymbol)) {
                        eur = new BigDecimal(String.valueOf(eur.add(money.multiply(currencyToEurRate.get(currency)))));
                    }
                }
            }
        }
        return eur;
    }
}
