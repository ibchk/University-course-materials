package ee.taltech.iti0200.kittenshelter;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShelterTest {

    public static final int MONEY2000 = 2000;
    public static final int MONEY9090 = 9090;
    public static final int MONEY11 = 11;
    public static final int MONEY110 = 110;
    public static final int KITTENCOUNT7 = 7;
    public static final int KITTENCOUNT6 = 6;


    Shelter varjupaik = new Shelter("Kiisu", "Tallinn", 5, MONEY2000);
    Human mart = new Human("Mart", 3, 100);
    Kitten cat1 = new Kitten("Cat1", Kitten.Color.BLACK, Kitten.LifeStage.YOUNG, Kitten.CoatLength.SHORTHAIR,
            Kitten.Vaccinated.YES, Kitten.UniqueChar.FAT);
    Kitten cat2 = new Kitten("Cat2", Kitten.Color.WHITE, Kitten.LifeStage.MIDDLE, Kitten.CoatLength.HAIRLESS,
            Kitten.Vaccinated.NO, Kitten.UniqueChar.ACTIVE);
    Kitten cat3 = new Kitten("Cat3", Kitten.Color.CREAM, Kitten.LifeStage.OLD, Kitten.CoatLength.SEMILONGHAIR,
            Kitten.Vaccinated.YES, Kitten.UniqueChar.STREETCAT);
    Kitten cat4 = new Kitten("Cat4", Kitten.Color.GINGER, Kitten.LifeStage.YOUNG, Kitten.CoatLength.LONGHAIR,
            Kitten.Vaccinated.YES, Kitten.UniqueChar.FRIENDLY);
    Kitten cat5 = new Kitten("Cat5", Kitten.Color.GREY, Kitten.LifeStage.OLD, Kitten.CoatLength.SHORTHAIR,
            Kitten.Vaccinated.NO, Kitten.UniqueChar.DRUGLOVER);
    Kitten cat6 = new Kitten("Cat6", Kitten.Color.WHITE, Kitten.LifeStage.YOUNG, Kitten.CoatLength.SHORTHAIR,
            Kitten.Vaccinated.YES, Kitten.UniqueChar.HOMESITTER);

    @org.junit.jupiter.api.Test
    void checkCorrectClassMaker() {
        assertEquals("Kiisu", varjupaik.getName());
        assertEquals("Tallinn", varjupaik.getPlace());
        assertEquals(5, varjupaik.getKittenMaxCount());
        assertEquals(MONEY2000, varjupaik.getMoneyAmount());
        assertEquals(0, varjupaik.getShelterLivingDays());
        assertEquals("Mart", mart.getName());
        assertEquals(3, mart.getKittensMaxCount());
        assertEquals(100, mart.getMoney());
        assertEquals("Cat1", cat1.getName());
        assertEquals(Kitten.CoatLength.SHORTHAIR, cat1.getCoatLength());
        assertEquals(Kitten.Color.BLACK, cat1.getColor());
        assertEquals(Kitten.LifeStage.YOUNG, cat1.getLifeStage());
        assertEquals(Kitten.Vaccinated.YES, cat1.getVaccine());
        assertEquals(Kitten.UniqueChar.FAT, cat1.getUniqueCharacteristic());
    }

    @org.junit.jupiter.api.Test
    void tryToAddMoreThanMight() {
        varjupaik.addKitten(cat1);
        varjupaik.addKitten(cat2);
        varjupaik.addKitten(cat3);
        varjupaik.addKitten(cat4);
        varjupaik.addKitten(cat5);
        varjupaik.addKitten(cat6);
        assertEquals(5, varjupaik.getKittens().size());
    }

    @org.junit.jupiter.api.Test
    void canAdoptAll() {
        varjupaik.setKittenMaxCount(KITTENCOUNT7);
        varjupaik.addKitten(cat1);
        varjupaik.addKitten(cat2);
        varjupaik.addKitten(cat3);
        varjupaik.addKitten(cat4);
        varjupaik.addKitten(cat5);
        varjupaik.addKitten(cat6);
        ArrayList<Object> good = new ArrayList<>();
        ArrayList<Object> bad = new ArrayList<>();
        assertEquals(0, varjupaik.kittensToAdopt(good, bad).size());
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.nextDay();
        assertEquals(KITTENCOUNT6, varjupaik.kittensToAdopt(good, bad).size());
    }

    @org.junit.jupiter.api.Test
    void adoptOnlyIfCan() {
        varjupaik.setKittenMaxCount(KITTENCOUNT7);
        varjupaik.addKitten(cat1);
        varjupaik.addKitten(cat2);
        varjupaik.addKitten(cat3);
        varjupaik.addKitten(cat4);
        varjupaik.addKitten(cat5);
        varjupaik.addKitten(cat6);
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.setMoneyAmount(MONEY9090);
        ArrayList<Object> good = new ArrayList<>(Collections.singletonList(Kitten.Color.WHITE));
        ArrayList<Object> bad = new ArrayList<>();
        assertEquals(2, varjupaik.kittensToAdopt(good, bad).size());
        good.add(Kitten.LifeStage.YOUNG);
        assertEquals(1, varjupaik.kittensToAdopt(good, bad).size());
        bad.add(Kitten.CoatLength.SHORTHAIR);
        good.remove(Kitten.LifeStage.YOUNG);
        assertEquals(1, varjupaik.kittensToAdopt(good, bad).size());
    }

    @org.junit.jupiter.api.Test
    void adoptCheckIfCantAndIfCan() {
        varjupaik.setKittenMaxCount(KITTENCOUNT7);
        varjupaik.addKitten(cat1);
        varjupaik.addKitten(cat2);
        varjupaik.addKitten(cat3);
        varjupaik.addKitten(cat4);
        varjupaik.addKitten(cat5);
        varjupaik.addKitten(cat6);
        assertFalse(varjupaik.adopt(mart, cat2));
        mart.setMoney(MONEY11);
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.nextDay();
        varjupaik.nextDay();
        assertFalse(varjupaik.adopt(mart, cat2));
        mart.setMoney(MONEY110);
        assertTrue(varjupaik.adopt(mart, cat2));
    }
}
