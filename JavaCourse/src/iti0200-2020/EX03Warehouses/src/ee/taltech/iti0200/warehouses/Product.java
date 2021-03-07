package ee.taltech.iti0200.warehouses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {
    private String name;
    private BigDecimal weight;
    private BigDecimal grossPrice;
    private BigDecimal netPrice;

    public Product(String name, BigDecimal weight, BigDecimal netPrice, BigDecimal grossPrice) {
        this.name = name;
        this.weight = weight;
        this.netPrice = netPrice;
        this.grossPrice = grossPrice;
    }

    public String getProductDescription() {
        return name + " " + grossPrice + " € " + weight + " kg";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    /**
     * Calculates the profitability percentage.
     *
     * @return the profitability percentage
     */
    public BigDecimal getProfitabilityPercentage() {
        return grossPrice.subtract(netPrice).divide(grossPrice, 10, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(100)).setScale(4, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name)
                && Objects.equals(weight, product.weight)
                && Objects.equals(netPrice, product.netPrice)
                && Objects.equals(grossPrice, product.grossPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, netPrice, grossPrice);
    }
}
