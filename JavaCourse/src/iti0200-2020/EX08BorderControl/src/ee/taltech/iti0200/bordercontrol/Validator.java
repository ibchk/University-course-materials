package ee.taltech.iti0200.bordercontrol;

import ee.taltech.iti0200.bordercontrol.entity.Goods;
import ee.taltech.iti0200.bordercontrol.entity.Person;
import ee.taltech.iti0200.bordercontrol.entity.Vehicle;

public interface Validator {
    boolean isInList(Goods goods);

    boolean isInList(Person person);

    boolean isInList(Vehicle vehicle);
}
