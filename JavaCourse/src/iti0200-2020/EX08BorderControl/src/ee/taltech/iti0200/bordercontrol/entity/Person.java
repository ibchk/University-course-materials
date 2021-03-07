package ee.taltech.iti0200.bordercontrol.entity;

import ee.taltech.iti0200.bordercontrol.Validator;

public class Person implements BorderEntity {

    String name;
    String idCode;

    public Person(String name, String idCode) {
        this.idCode = idCode;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Long getBorderCrossingId() {
        return Long.parseLong(idCode);
    }

    public void setBorderCrossingId(Long borderCrossingId) {
        idCode = borderCrossingId.toString();
    }

    @Override
    public boolean isInLists(Validator validator) {
        return validator.isInList(this);
    }
}
