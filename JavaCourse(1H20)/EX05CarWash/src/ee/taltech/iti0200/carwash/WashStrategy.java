import static java.util.Arrays.asList;

import java.util.List;

public abstract class WashStrategy {

    public static final int DURATION = 0;
    public static final int PRICE = 0;


    private final List<String> blacklistedClients = asList("Mait", "Mari", "Mati", "Kai");
    private int sessionDuration = DURATION;
    private int sessionPrice = PRICE;

    public final boolean isClientBlacklisted(String name) {
        return blacklistedClients.contains(name);
    }

    /**
     * Overridden by concrete wash strategy.
     */
    public abstract void wash(Car car, CarOwner owner);

    /**
     * Overridden by concrete dry strategy.
     */
    public abstract void dry(Car car, CarOwner owner);

    /**
     * Calculates wash and dry price based on wash strategy used.
     *
     * @return the wash and dry price
     */
    public abstract int getWashAndDryPrice();

    /**
     * Calculates wash price based on wash strategy used.
     *
     * @return the wash price
     */
    public abstract int getWashPrice();

    public void setSessionDuration(int duration) {
        sessionDuration = duration;
    }

    public int getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionPrice(int price) {
        sessionPrice = price;
    }

    public int getSessionPrice() {
        return sessionPrice;
    }
}
