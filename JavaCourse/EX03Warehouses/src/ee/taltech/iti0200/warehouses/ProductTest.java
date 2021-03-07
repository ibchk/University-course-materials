package ee.taltech.iti0200.warehouses;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void setName() {
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("30");
        BigDecimal grossPrice = new BigDecimal("30");
        BigDecimal netPrice = new BigDecimal("30");
        Product newProduct = new Product(name, weight, netPrice, grossPrice);
        assertEquals(name + " " + grossPrice + " € " + weight + " kg", newProduct.getProductDescription());
        newProduct.setName("NikeCrossowki");
        assertEquals(newProduct.getName() + " " + grossPrice
                + " € " + weight + " kg", newProduct.getProductDescription());
    }

    @Test
    void setWeight() {
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("30");
        BigDecimal grossPrice = new BigDecimal("30");
        BigDecimal netPrice = new BigDecimal("30");
        Product newProduct = new Product(name, weight, netPrice, grossPrice);
        newProduct.setWeight(new BigDecimal("45"));
        assertEquals(name + " " + grossPrice
                + " € " + newProduct.getWeight() + " kg", newProduct.getProductDescription());
    }

    @Test
    void setNetPrice() {
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("30");
        BigDecimal grossPrice = new BigDecimal("30");
        BigDecimal netPrice = new BigDecimal("30");
        Product newProduct = new Product(name, weight, netPrice, grossPrice);
        newProduct.setNetPrice(new BigDecimal("2222"));
        assertEquals(new BigDecimal("2222"), newProduct.getNetPrice());
    }

    @Test
    void setGrossPrice() {
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("30");
        BigDecimal grossPrice = new BigDecimal("30");
        BigDecimal netPrice = new BigDecimal("30");
        Product newProduct = new Product(name, weight, netPrice, grossPrice);
        newProduct.setGrossPrice(new BigDecimal("3000"));
        assertEquals(name + " " + newProduct.getGrossPrice()
                + " € " + weight + " kg", newProduct.getProductDescription());
    }

    @Test
    void getProfitabilityPercentage() {
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("30");
        BigDecimal grossPrice = new BigDecimal("60");
        BigDecimal netPrice = new BigDecimal("30");
        Product newProduct = new Product(name, weight, netPrice, grossPrice);
        assertEquals(new BigDecimal("50.0000"), newProduct.getProfitabilityPercentage());
        newProduct.setNetPrice(new BigDecimal("60"));
        assertEquals(new BigDecimal("0.0000"), newProduct.getProfitabilityPercentage());
        newProduct.setNetPrice(new BigDecimal("80"));
        assertEquals(new BigDecimal("-33.3333"), newProduct.getProfitabilityPercentage());
    }
}
