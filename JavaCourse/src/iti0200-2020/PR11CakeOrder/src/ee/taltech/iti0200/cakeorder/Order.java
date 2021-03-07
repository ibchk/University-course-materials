package ee.taltech.iti0200.cakeorder;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    public int order_id;
    public BigDecimal total = new BigDecimal("0.00");
    public List<Cake> cakes;
}
