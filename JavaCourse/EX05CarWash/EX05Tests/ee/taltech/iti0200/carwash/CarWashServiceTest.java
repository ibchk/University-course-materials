import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CarWashServiceTest {

    private static final int BALANCE72K = 72000;
    private static final int BALANCE1500 = 1500;
    private static final int DIRTINESS80 = 80;

    CarOwner kai = new CarOwner("Kai", BALANCE72K);
    Car audi = new Car(5);
    CarOwner siim = new CarOwner("Siim", BALANCE1500);
    Car honda = new Car(DIRTINESS80);
    CarOwner martin = new CarOwner("Martin", 0);
    Car bmw = new Car(100);
    CarWashService carWashService = new CarWashService();

    PremiumWash premiumWash = new PremiumWash();
    RegularWash regularWash = new RegularWash();
    CheapWash cheapWash = new CheapWash();

    @Test
    void wash() {
        assertFalse(carWashService.wash(audi, kai));
        assertFalse(carWashService.wash(audi, siim));
        carWashService.setWashStrategy(premiumWash);
        assertFalse(carWashService.wash(audi, kai));
        assertTrue(carWashService.wash(audi, siim));
        assertTrue(carWashService.wash(bmw, siim));
        carWashService.setWashStrategy(regularWash);
        assertTrue(carWashService.wash(audi, siim));
        assertFalse(carWashService.wash(honda, martin));
        carWashService.setWashStrategy(cheapWash);
        assertFalse(carWashService.washAndDry(honda, martin));
        assertTrue(carWashService.washAndDry(audi, siim));
    }

    @Test
    void washAndDry() {
        assertFalse(carWashService.washAndDry(audi, kai));
        assertFalse(carWashService.washAndDry(audi, siim));
        carWashService.setWashStrategy(premiumWash);
        assertFalse(carWashService.washAndDry(audi, kai));
        assertTrue(carWashService.washAndDry(audi, siim));
        assertTrue(carWashService.washAndDry(bmw, siim));
        carWashService.setWashStrategy(regularWash);
        assertTrue(carWashService.washAndDry(audi, siim));
        assertFalse(carWashService.washAndDry(honda, martin));
        carWashService.setWashStrategy(regularWash);
        assertFalse(carWashService.washAndDry(honda, martin));
        assertTrue(carWashService.washAndDry(audi, siim));
        assertEquals(10, cheapWash.getWashPrice());
        assertEquals(0, cheapWash.getSessionDuration());
        assertEquals(0, cheapWash.getSessionPrice());
    }
}
