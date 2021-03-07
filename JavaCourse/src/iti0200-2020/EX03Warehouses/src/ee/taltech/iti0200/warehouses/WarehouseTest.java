package ee.taltech.iti0200.warehouses;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WarehouseTest {

    public static final int MAXFORLOOP = 30;
    public static final int LONG3PLUS5 = 8;
    public static final int RANDOMLONG11 = 11;
    public static final int AGE23 = 23;


    @Test
    void getAmount() {
        Warehouse newWarehouse = new Warehouse("Tallinn");
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("4");
        BigDecimal grossPrice = new BigDecimal("" + "3");
        BigDecimal netPrice = new BigDecimal("30");
        Product oneProduct = new Product(name, weight, netPrice, grossPrice);
        assertEquals((long) 0, newWarehouse.getAmount(oneProduct));
        for (int i = 1; i <= MAXFORLOOP; i++) {
            String name2 = "AdidasCrossowki" + i;
            BigDecimal weight2 = new BigDecimal("" + i);
            BigDecimal grossPrice2 = new BigDecimal("" + i);
            BigDecimal netPrice2 = new BigDecimal("" + i);
            Product newProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
            Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(newProduct2, (long) i);
            newWarehouse.addProduct(entry);
        }
        String name3 = "AdidasCrossowki3";
        BigDecimal weight3 = new BigDecimal("3");
        BigDecimal grossPrice3 = new BigDecimal("3");
        BigDecimal netPrice3 = new BigDecimal("3");
        Product newProduct3 = new Product(name3, weight3, netPrice3, grossPrice3);
        assertEquals((long) 0, newWarehouse.getAmount(oneProduct));
        assertEquals((long) 3, newWarehouse.getAmount(newProduct3));
        Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(newProduct3, (long) 5);
        newWarehouse.addProduct(entry);
        assertEquals((long) LONG3PLUS5, newWarehouse.getAmount(newProduct3));
    }

    @Test
    void getAddress() {
        Warehouse newWarehouse = new Warehouse("Tallinn");
        assertEquals("Tallinn", newWarehouse.getAddress());
    }

    @Test
    void hasProduct() {
        Warehouse newWarehouse = new Warehouse("Tallinn");
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("4");
        BigDecimal grossPrice = new BigDecimal("" + "3");
        BigDecimal netPrice = new BigDecimal("30");
        Product oneProduct = new Product(name, weight, netPrice, grossPrice);
        assertFalse(newWarehouse.hasProduct(oneProduct));
        Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(oneProduct, (long) 5);
        newWarehouse.addProduct(entry);
        newWarehouse.addProduct(entry);
        assertTrue(newWarehouse.hasProduct(oneProduct));
    }

    @Test
    void hasEnoughProduct() {
        Warehouse newWarehouse = new Warehouse("Tallinn");
        String name = "AdidasCrossowki";
        BigDecimal weight = new BigDecimal("4");
        BigDecimal grossPrice = new BigDecimal("" + "3");
        BigDecimal netPrice = new BigDecimal("30");
        Product oneProduct = new Product(name, weight, netPrice, grossPrice);
        assertFalse(newWarehouse.hasProduct(oneProduct));
        Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(oneProduct, (long) 5);
        newWarehouse.addProduct(entry);
        assertFalse(newWarehouse.hasEnoughProduct(oneProduct, (long) 10));
        newWarehouse.addProduct(entry);
        assertTrue(newWarehouse.hasEnoughProduct(oneProduct, (long) 10));
        assertFalse(newWarehouse.hasEnoughProduct(oneProduct, (long) RANDOMLONG11));
        String name2 = "AdidasCrossowkii";
        BigDecimal weight2 = new BigDecimal("4");
        BigDecimal grossPrice2 = new BigDecimal("3");
        BigDecimal netPrice2 = new BigDecimal("30");
        Product oneProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
        assertFalse(newWarehouse.hasEnoughProduct(oneProduct2, (long) 1));
    }

    @Test
    void getWorkers() {
        Warehouse newWarehouse = new Warehouse("Tallinn");
        Set<Worker> workers = new HashSet<>();
        Worker worker = new Worker("Ivan", "Lumberg", "2323", AGE23);
        assertEquals(workers, newWarehouse.getWorkers());
        newWarehouse.addWorker(worker);
        workers.add(worker);
        assertEquals(workers, newWarehouse.getWorkers());
        Worker worker2 = new Worker("Ivan", "Lumberg", "2323", AGE23);
        assertTrue(worker.equals(worker2));

    }

    @Test
    void getInventoryValue() {
        Warehouse newWarehouse = new Warehouse("Tallinn");
        BigDecimal value = new BigDecimal("0");
        assertEquals(value, newWarehouse.getInventoryValue());
        for (int i = 1; i <= MAXFORLOOP; i++) {
            String name2 = "AdidasCrossowki" + i;
            BigDecimal weight2 = new BigDecimal("" + i);
            BigDecimal grossPrice2 = new BigDecimal("" + i);
            BigDecimal netPrice2 = new BigDecimal("" + i);
            Product newProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
            Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(newProduct2, (long) i);
            newWarehouse.addProduct(entry);
            value = new BigDecimal(value.intValue() + i * i);
        }
        assertEquals(value, newWarehouse.getInventoryValue());
    }
}
