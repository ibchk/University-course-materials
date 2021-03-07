package ee.taltech.iti0200.bordercontrol;

import ee.taltech.iti0200.bordercontrol.database.Database;
import ee.taltech.iti0200.bordercontrol.database.DatabaseImpl;
import ee.taltech.iti0200.bordercontrol.entity.BorderEntity;
import ee.taltech.iti0200.bordercontrol.entity.Goods;
import ee.taltech.iti0200.bordercontrol.entity.Person;
import ee.taltech.iti0200.bordercontrol.entity.Vehicle;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryBorderControlTest {

    BorderEntity socks1 = new Goods(Long.getLong("1111"));
    BorderEntity socks2 = new Goods(Long.getLong("2222"));
    BorderEntity socks3 = new Goods(Long.getLong("3three3"));
    BorderEntity mark = new Person("mark", "1234");
    BorderEntity sten = new Person("sten", "2345");
    BorderEntity vlad = new Person("vlad", "3456");
    BorderEntity car1 = new Vehicle("123");
    BorderEntity car2 = new Vehicle("234");
    BorderEntity car3 = new Vehicle("345");
    Database database = new DatabaseImpl();
    Validator validator = new BorderValidator(database);
    CountryBorderControl borderPiont = new CountryBorderControl("eesti", "narva", validator);

    @org.junit.jupiter.api.Test
    void processBorderCrossersControl2illegalGoods() {
        List<Long> goodsIllegal = new LinkedList<>(Arrays.asList(socks1.getBorderCrossingId(),
                socks3.getBorderCrossingId()));
        List<BorderEntity> crossers = new LinkedList<>(Arrays.asList(socks1, socks2, socks3));
        database.setIllegalGoods(goodsIllegal);
        assertEquals(0, borderPiont.processBorderCrossers(crossers).size());
    }

    @org.junit.jupiter.api.Test
    void processBorderCrossersParallelControl2illegalGoods() {
        List<Long> goodsIllegal = new LinkedList<>(Arrays.asList(socks1.getBorderCrossingId(),
                socks2.getBorderCrossingId()));
        List<BorderEntity> crossers = new LinkedList<>(Arrays.asList(socks1, socks2, socks3));
        database.setIllegalGoods(goodsIllegal);
        assertEquals(0, borderPiont.processBorderCrossers(crossers).size());
    }

    @org.junit.jupiter.api.Test
    void processBorderCrossersControl2IllegalPersons() {
        List<String> missingPersons = new LinkedList<>(Collections.singletonList(mark.getBorderCrossingId()
                .toString()));
        List<String> terrirists = new LinkedList<>(Collections.singletonList(sten.getBorderCrossingId().toString()));
        List<BorderEntity> crossers = new LinkedList<>(Arrays.asList(sten, mark, vlad));
        database.setMissingPersons(missingPersons);
        database.setTerrorists(terrirists);
        assertEquals(1, borderPiont.processBorderCrossers(crossers).size());
    }

    @org.junit.jupiter.api.Test
    void processBorderCrossersParallelControl2IllegalPersons() {
        List<String> missingPersons = new LinkedList<>(Collections.singletonList("vlad"));
        List<String> terrirists = new LinkedList<>(Collections.singletonList("sten"));
        List<BorderEntity> crossers = new LinkedList<>(Arrays.asList(sten, mark, vlad));
        database.setMissingPersons(missingPersons);
        database.setTerrorists(terrirists);
        assertEquals(1, borderPiont.processBorderCrossers(crossers).size());
    }

    @org.junit.jupiter.api.Test
    void processBorderCrossersControl2illegalCars() {
        List<String> carsIllegal = new LinkedList<>(Arrays.asList(car1.getBorderCrossingId().toString(),
                car2.getBorderCrossingId().toString()));
        List<BorderEntity> crossers = new LinkedList<>(Arrays.asList(car2, car1, car3));
        database.setStolenVehicles(carsIllegal);
        assertEquals(1, borderPiont.processBorderCrossers(crossers).size());
    }

    @org.junit.jupiter.api.Test
    void processBorderCrossersParallelControl2illegalCars() {
        List<String> carsIllegal = new LinkedList<>(Arrays.asList(car1.getBorderCrossingId().toString(),
                car2.getBorderCrossingId().toString()));
        List<BorderEntity> crossers = new LinkedList<>(Arrays.asList(car2, car1, car3));
        database.setStolenVehicles(carsIllegal);
        assertEquals(1, borderPiont.processBorderCrossers(crossers).size());
    }
}
