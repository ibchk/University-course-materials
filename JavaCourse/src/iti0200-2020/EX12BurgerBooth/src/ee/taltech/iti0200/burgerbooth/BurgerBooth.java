package ee.taltech.iti0200.burgerbooth;

import ee.taltech.iti0200.burgerbooth.terminal.Terminal;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class BurgerBooth {

    public static final int BILLLENGTH = 55;
    public static final int MINSECHOUR = 60;

    private Terminal terminal;
    private List<String> orders = new ArrayList<>();
    private List<Food> menu = new ArrayList<>();
    private Map<String, Integer> ingredientsInStock = new HashMap<>();
    private Double money = 0.0;

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public String finishOrder() {
        if (terminal != null) {
            Map<Food, Integer> order = terminal.getOrder();
            int billLength = BILLLENGTH;
            LocalDateTime now = LocalDateTime.now();
            String bill = "";
            int billNr = orders.size() + 1;
            bill += "=".repeat(billLength) + "\nǁ" + " ".repeat(billLength - 2) + "ǁ\nǁ" + addSpaces(billLength,
                    "Order nr: " + billNr) + "ǁ\nǁ" + addSpaces(billLength, "Order time: "
                    + now.toLocalDate() + " " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond()) + "ǁ\n";
            now = updateTime(now, terminal.getTimeForCooking());
            bill += "ǁ" + addSpaces(billLength, "Order finish time: " + now.toLocalDate() + " " + now.getHour()
                    + ":" + now.getMinute() + ":" + now.getSecond()) + "ǁ\nǁ" + " ".repeat(billLength - 2) + "ǁ\n";
            double sumPrice = 0.0;
            for (Food food : order.keySet()) {
                bill += "ǁ" + addSpaces(billLength, order.get(food) + " " + food.getName() + " - "
                        + Math.round(food.getPrice() * order.get(food) * 100.0) / 100.0 + " $") + "ǁ\n";
                sumPrice += Math.round(food.getPrice() * order.get(food) * 100.0) / 100.0;
            }
            money += sumPrice;
            bill += "ǁ" + addSpaces(billLength, "Total: " + sumPrice + " $") + "ǁ\nǁ"
                    + " ".repeat(billLength - 2) + "ǁ\n" + "=".repeat(billLength);
            orders.add(bill);
            terminal.removeOrder();
            return bill;
        } else return "Terminal problems.";
    }

    public Map<Food, Integer> getOrder() {
        if (terminal != null) {
            return terminal.getOrder();
        } else return new HashMap<>();
    }

    public void addFoodToMenu(Food food) {
        menu.add(food);
    }

    private String addSpaces(int stringLength, String string) {
        return string + " ".repeat(stringLength - string.length() - 2);
    }

    private LocalDateTime updateTime(LocalDateTime time, double cookTime) {
        time = time.plusHours((long) Math.floor(cookTime / MINSECHOUR));
        time = time.plusMinutes((long) Math.floor(cookTime % MINSECHOUR));
        time = time.plusSeconds(Math.round(MINSECHOUR * (cookTime - Math.floor(cookTime))));
        return time;
    }

    public List<String> getAllOrders() {
        return orders;
    }

    private boolean checkIfEnoughProducts(Food food) {
        Map<String, Integer> copy = new HashMap<>(ingredientsInStock);
        for (String ingredient : food.getIngredients()) {
            if (copy.containsKey(ingredient) && copy.get(ingredient) > 0) {
                copy.replace(ingredient, copy.get(ingredient) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    private void cook(Food food) {
        for (String ingredient : food.getIngredients()) {
            ingredientsInStock.replace(ingredient, ingredientsInStock.get(ingredient) - 1);
        }
    }

    public void addIngredient(String ingredient, int quantity) {
        if (ingredientsInStock.containsKey(ingredient)) {
            ingredientsInStock.replace(ingredient, ingredientsInStock.get(ingredient) + quantity);
        } else {
            ingredientsInStock.put(ingredient, quantity);
        }
    }

    public List<Food> getMenu() {
        List<Food> menuInStock = new LinkedList<>();
        for (Food food : menu) {
            if (checkIfEnoughProducts(food)) {
                menuInStock.add(food);
            }
        }
        return menuInStock;
    }

    public String addFoodToOrder(Food food) {
        if (terminal != null && getMenu().contains(food)) {
            cook(food);
            terminal.addFood(food);
            return food.getName() + " was added to the order.";
        } else if (terminal != null) {
            return food.getName() + " is not available.";
        } else return "Terminal problems.";
    }

    public void removeFoodFromOrder(Food food) {
        if (terminal != null) {
            terminal.removeFood(food);
        }
    }

    public Map<String, Integer> getIngredientsInStock() {
        return ingredientsInStock;
    }

    public Double getMoney() {
        return money;
    }
}
