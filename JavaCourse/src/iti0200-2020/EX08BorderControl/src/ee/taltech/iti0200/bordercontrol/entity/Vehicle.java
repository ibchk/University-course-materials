package ee.taltech.iti0200.bordercontrol.entity;

import ee.taltech.iti0200.bordercontrol.Validator;

public class Vehicle implements BorderEntity {

    String vin;

    public Vehicle(String vin) {
        this.vin = vin;
    }

    @Override
    public Long getBorderCrossingId() {
        return Long.parseLong(vin);
    }

    public void setBorderCrossingId(Long borderCrossingId) {
        vin = borderCrossingId.toString();
    }

    @Override
    public boolean isInLists(Validator validator) {
        return validator.isInList(this);
    }
}
