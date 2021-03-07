package ee.taltech.iti0200.bordercontrol.entity;

import ee.taltech.iti0200.bordercontrol.Validator;

public interface BorderEntity {
    Long getBorderCrossingId();

    void setBorderCrossingId(Long borderCrossingId);

    boolean isInLists(Validator validator);
}
