package ee.taltech.iti0200.bordercontrol;

import ee.taltech.iti0200.bordercontrol.database.Database;
import ee.taltech.iti0200.bordercontrol.entity.Goods;
import ee.taltech.iti0200.bordercontrol.entity.Person;
import ee.taltech.iti0200.bordercontrol.entity.Vehicle;

import java.util.LinkedList;
import java.util.List;

public class BorderValidator implements Validator {
    Database database;

    public BorderValidator(Database database) {
        this.database = database;
    }

    @Override
    public boolean isInList(Goods goods) {
        return !database.getIllegalGoods().contains(goods.getBorderCrossingId());
    }

    @Override
    public boolean isInList(Person person) {
        List<String> listWithAll = new LinkedList<>();
        listWithAll.addAll(database.getMissingPersons());
        listWithAll.addAll(database.getTerrorists());
        return !listWithAll.contains(person.getBorderCrossingId().toString())
                && !listWithAll.contains(person.getName());
    }

    @Override
    public boolean isInList(Vehicle vehicle) {
        return !database.getStolenVehicles().contains(vehicle.getBorderCrossingId().toString());
    }
}
