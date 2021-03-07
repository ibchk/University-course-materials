package ee.taltech.iti0200.warehouses;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CompanyTest {

    public static final int MAXFORLOOP = 30;
    public static final int AGE24 = 24;

    @Test
    void getName() {
        Company newCompany = new Company("Selver");
        assertEquals("Selver", newCompany.getName());
    }

    @Test
    void getCheapestProductForCustomer() {
        Company newCompany = new Company("Selver");
        Warehouse newWarehouse = new Warehouse("Tallinn");
        Warehouse newWarehouse2 = new Warehouse("Jõhvi");
        assertEquals(Optional.empty(), newCompany.getCheapestProductForCustomer());
        for (int i = 1; i <= MAXFORLOOP; i++) {
            String name = "AdidasCrossowki" + i;
            BigDecimal weight = new BigDecimal("" + i + 1);
            BigDecimal grossPrice = new BigDecimal("" + i + 1);
            BigDecimal netPrice = new BigDecimal("" + i + 1);
            Product newProduct = new Product(name, weight, netPrice, grossPrice);
            Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(newProduct, (long) i + 1);
            newWarehouse.addProduct(entry);
            String name2 = "AdidasCrossowki" + i;
            BigDecimal weight2 = new BigDecimal("" + i);
            BigDecimal grossPrice2 = new BigDecimal("" + i);
            BigDecimal netPrice2 = new BigDecimal("" + i);
            Product newProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
            Map.Entry<Product, Long> entry2 = new AbstractMap.SimpleEntry<>(newProduct2, (long) i);
            newCompany.addProduct(newProduct);
            newCompany.addProduct(newProduct2);
            newWarehouse2.addProduct(entry2);
        }
        newCompany.addWarehouse(newWarehouse);
        newCompany.addWarehouse(newWarehouse2);
        BigDecimal one = new BigDecimal("1");
        Product rightProduct = new Product("AdidasCrossowki" + 1, one, one, one);
        assertEquals(Optional.of(rightProduct), newCompany.getCheapestProductForCustomer());
        BigDecimal thirty = new BigDecimal("301");
        Product rightProduct2 = new Product("AdidasCrossowki30", thirty, thirty, thirty);
        assertEquals(Optional.of(rightProduct2), newCompany.getMostExpensiveProductForCustomer());
    }

    @Test
    void reportInventory() {
        Company newCompany = new Company("Selver");
        Warehouse newWarehouse = new Warehouse("Tallinn");
        Warehouse newWarehouse2 = new Warehouse("Jõhvi");
        Map<Product, Long> currentStateOfProducts = new HashMap<>();
        for (int i = 1; i <= MAXFORLOOP; i++) {
            String name = "AdidasCrossowki" + i;
            BigDecimal weight = new BigDecimal("" + i + 1);
            BigDecimal grossPrice = new BigDecimal("" + i + 1);
            BigDecimal netPrice = new BigDecimal("" + i + 1);
            Product newProduct = new Product(name, weight, netPrice, grossPrice);
            Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(newProduct, (long) i + 1);
            currentStateOfProducts.put(newProduct, (long) i + 1);
            newWarehouse.addProduct(entry);
            String name2 = "AdidasCrossowki" + i;
            BigDecimal weight2 = new BigDecimal("" + i);
            BigDecimal grossPrice2 = new BigDecimal("" + i);
            BigDecimal netPrice2 = new BigDecimal("" + i);
            Product newProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
            Map.Entry<Product, Long> entry2 = new AbstractMap.SimpleEntry<>(newProduct2, (long) i);
            currentStateOfProducts.put(newProduct2, (long) i);
            newCompany.addProduct(newProduct);
            newCompany.addProduct(newProduct2);
            newWarehouse2.addProduct(entry2);
        }
        newCompany.addWarehouse(newWarehouse);
        newCompany.addWarehouse(newWarehouse2);
        assertEquals(currentStateOfProducts, newCompany.reportInventory());
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(newWarehouse);
        warehouses.add(newWarehouse2);
        assertEquals(warehouses, newCompany.getWarehouses());
    }

    @Test
    void getAvailability() {
        Company newCompany = new Company("Selver");
        Warehouse newWarehouse = new Warehouse("Tallinn");
        Warehouse newWarehouse2 = new Warehouse("Jõhvi");
        for (int i = 1; i <= MAXFORLOOP; i++) {
            String name = "AdidasCrossowki" + i + 1;
            BigDecimal weight = new BigDecimal("" + i + 1);
            BigDecimal grossPrice = new BigDecimal("" + i + 1);
            BigDecimal netPrice = new BigDecimal("" + i + 1);
            Product newProduct = new Product(name, weight, netPrice, grossPrice);
            Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(newProduct, (long) i + 1);
            newWarehouse.addProduct(entry);
            String name2 = "AdidasCrossowki" + i;
            BigDecimal weight2 = new BigDecimal("" + i);
            BigDecimal grossPrice2 = new BigDecimal("" + i);
            BigDecimal netPrice2 = new BigDecimal("" + i);
            Product newProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
            Map.Entry<Product, Long> entry2 = new AbstractMap.SimpleEntry<>(newProduct2, (long) i);
            System.out.println(name);
            System.out.println(weight);
            System.out.println(name2);
            System.out.println(weight2);
            System.out.println();
            newCompany.addProduct(newProduct);
            newCompany.addProduct(newProduct2);
            newWarehouse2.addProduct(entry2);
        }
        newCompany.addWarehouse(newWarehouse);
        newCompany.addWarehouse(newWarehouse2);
        List<Warehouse> listOfWarehouse1 = new LinkedList<>();
        listOfWarehouse1.add(newWarehouse);
        List<Warehouse> listOfWarehouse2 = new LinkedList<>();
        listOfWarehouse2.add(newWarehouse2);
        List<Warehouse> listOfWarehouses = new LinkedList<>();
        listOfWarehouses.add(newWarehouse2);
        listOfWarehouses.add(newWarehouse);
        String name = "AdidasCrossowki1";
        BigDecimal weight = new BigDecimal("1");
        BigDecimal grossPrice = new BigDecimal("1");
        BigDecimal netPrice = new BigDecimal("1");
        Product newProduct = new Product(name, weight, netPrice, grossPrice);
        assertEquals(listOfWarehouse2, newCompany.getAvailability(newProduct));
        String name2 = "AdidasCrossowki11";
        BigDecimal weight2 = new BigDecimal("11");
        BigDecimal grossPrice2 = new BigDecimal("11");
        BigDecimal netPrice2 = new BigDecimal("11");
        Product newProduct2 = new Product(name2, weight2, netPrice2, grossPrice2);
        assertEquals(listOfWarehouses, newCompany.getAvailability(newProduct2));
        String name3 = "AdidasCrossowki181";
        BigDecimal weight3 = new BigDecimal("181");
        BigDecimal grossPrice3 = new BigDecimal("181");
        BigDecimal netPrice3 = new BigDecimal("181");
        Product newProduct3 = new Product(name3, weight3, netPrice3, grossPrice3);
        assertEquals(listOfWarehouse1, newCompany.getAvailability(newProduct3));
    }

    @Test
    void restockProduct() {
    }

    @Test
    void getCompanyWorkers() {
        Company newCompany = new Company("Selver");
        Set<Worker> allWorkers = new HashSet<>();
        assertEquals(allWorkers, newCompany.getCompanyWorkers());
        String firstName = "Mark";
        String lastName = "Mägi";
        String idCode = "40312132226";
        int age = AGE24;
        Worker newWorker = new Worker(firstName, lastName, idCode, age);
        newCompany.addOfficeWorker(newWorker);
        allWorkers.add(newWorker);
        assertEquals(allWorkers, newCompany.getCompanyWorkers());
    }

    @Test
    void getCompanyGoodsValue() {
        Company newCompany = new Company("Selver");
        assertEquals(new BigDecimal("0"), newCompany.getCompanyGoodsValue());
    }
}
