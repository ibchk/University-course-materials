package ee.taltech.iti0200.bordercontrol.entity;

import ee.taltech.iti0200.bordercontrol.Validator;


public class Goods implements BorderEntity {
    Long productId;
    Long productId1;

    public Goods(Long productId) {
        this.productId = productId;
    }

    @Override
    public Long getBorderCrossingId() {
        return productId;
    }

    public void setBorderCrossingId(Long borderCrossingId) {
        productId1 = borderCrossingId;
    }

    @Override
    public boolean isInLists(Validator validator) {
        return validator.isInList(this);
    }
}
