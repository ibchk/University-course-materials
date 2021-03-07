package ee.taltech.iti0200.cakeorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CakeOrderProcessor {

    public enum CakeOrderProcessorType {

        MAKE_DAIRY_FREE,
        COUNT_TOTAL_SUM,
        REMOVE_BEST_BEFORE_DAY_OVER

    }

    private CakeOrderProcessorType type;

    public CakeOrderProcessor(CakeOrderProcessorType type) {
        this.type = type;
    }

    public String process(String jsonInput) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Order order = gson.fromJson(jsonInput, Order.class);
        switch (type) {
            case MAKE_DAIRY_FREE:
                makeDairyFree(order);
                break;
            case COUNT_TOTAL_SUM:
                countTotalSum(order);
                break;
            case REMOVE_BEST_BEFORE_DAY_OVER:
                removeBB(order);
                break;
        }
        return gson.toJson(order);
    }

    public void makeDairyFree(Order order) {
        changeNameAndOrderId(order);
        order.total = null;
        for (Cake cake : order.cakes) {
            int len = cake.ingredients.size();
            List<String> newIng = new ArrayList<>();
            for (String product : cake.ingredients) {
                if (product.equals("milk")) {
                    newIng.add("plant-milk");
                } else if (product.equals("cream-cheese")) {
                    newIng.add("plant-cream-cheese");
                } else if (product.equals("yoghurt")) {
                    newIng.add("plant-yoghurt");
                } else {
                    newIng.add(product);
                    len--;
                }
            }
            MathContext m = new MathContext(2);
            cake.price = cake.price.add(cake.price.multiply(new BigDecimal(0.1 * len), m));
            cake.ingredients = newIng;
        }
    }

    public void countTotalSum(Order order) {
        MathContext m = new MathContext(2);
        changeNameAndOrderId(order);
        for (Cake cake : order.cakes) {
            order.total = order.total.add(cake.price.multiply(cake.kg, m));
        }
    }

    public void removeBB(Order order) {
        changeNameAndOrderId(order);
        order.total = null;
        ArrayList newCakeArray = new ArrayList();
        for (Cake cake : order.cakes) {
            ChronoLocalDateTime thisTime = LocalDateTime.parse(cake.BBD + "T00:00:00");
            if (LocalDateTime.now().compareTo(thisTime) < 1) {
                newCakeArray.add(cake);
            }
        }
        order.cakes = newCakeArray;
    }

    public void changeNameAndOrderId(Order order) {
        order.order_id += 1;
        for (Cake cake : order.cakes) {
            StringBuilder id = new StringBuilder();
            for (String word : cake.name.split("\\s+")) {
                id.append(word.charAt(0));
            }
            cake.cake_id = id.toString().toUpperCase() + id.length();
        }
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "  \"cakes\": [\n" +
                "    {\n" +
                "      \"name\": \"Sacher\",\n" +
                "      \"BBD\": \"2020-03-29\",\n" +
                "      \"price\": 14.00,\n" +
                "      \"kg\": 2.00,\n" +
                "      \"ingredients\": [\"flour\", \"chocolate\", \"milk\", \"sugar\", \"eggs\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"New York Cheesecake\",\n" +
                "      \"BBD\": \"2020-03-30\",\n" +
                "      \"price\": 10.00,\n" +
                "      \"kg\": 1.50,\n" +
                "      \"ingredients\": [\"flour\", \"cream-cheese\", \"milk\", \"sugar\", \"eggs\"]\n" +
                "\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        CakeOrderProcessor n = new CakeOrderProcessor(CakeOrderProcessorType.COUNT_TOTAL_SUM);
        str = n.process(str);
        System.out.println(IntStream.range(0, 10).filter(i -> i % 3 == 0).count());
    }
}
