public class CarWashService {
    private WashStrategy washStrategy;

    public void setWashStrategy(WashStrategy washStrategy) {
        this.washStrategy = washStrategy;
    }

    /**
     * Using provided WashStrategy to wash the owners car.
     *
     * @return the boolean if wash was successful
     */
    public boolean wash(Car car, CarOwner owner) {
        if (washStrategy == null || owner.getBalance() < washStrategy.getWashPrice()
                || washStrategy.isClientBlacklisted(owner.getName())) {
            return false;
        }
        washStrategy.wash(car, owner);
        return true;
    }

    /**
     * Using provided WashStrategy to wash and dry the owners car.
     *
     * @return the boolean if wash and dry was successful
     */
    public boolean washAndDry(Car car, CarOwner owner) {
        if (washStrategy == null || owner.getBalance() < washStrategy.getWashAndDryPrice()
                || washStrategy.isClientBlacklisted(owner.getName())) {
            return false;
        }
        washStrategy.wash(car, owner);
        washStrategy.dry(car, owner);
        return true;
    }
}
