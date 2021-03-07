package ee.taltech.iti0200.warehouses;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.AbstractMap;

public class Company {
    private String name;
    private Set<Warehouse> warehouses = new HashSet<>();
    private Set<Worker> officeWorkers = new HashSet<>();
    private Set<Product> products = new HashSet<>();

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the cheapest product for customer.
     *
     * @return the cheapest product
     */
    public Optional<Product> getCheapestProductForCustomer() {
        if (products.isEmpty()) {
            return Optional.empty();
        }
        Product s = null;
        for (Product product : products) {
            if (s == null || product.getGrossPrice().equals(product.getGrossPrice().min(s.getGrossPrice()))) {
                s = product;
            }
        }
        return Optional.of(s);
    }

    /**
     * Gets the most expensive product for customer.
     *
     * @return the most expensive product
     */
    public Optional<Product> getMostExpensiveProductForCustomer() {
        Product s = null;
        for (Product product : products) {
            if (s == null || product.getGrossPrice().equals(product.getGrossPrice().max(s.getGrossPrice()))) {
                s = product;
            }
        }
        return (!products.isEmpty()) ? Optional.of(s) : Optional.empty();
    }

    /**
     * Reports the current state of products in the warehouses.
     *
     * @return the current state of products in the warehouses
     */
    public Map<Product, Long> reportInventory() {
        Map<Product, Long> currentStateOfProducts = new HashMap<>();
        for (Product product : products) {
            long productAmount = 0;
            for (Warehouse warehouse : warehouses) {
                if (warehouse.hasProduct(product)) {
                    productAmount = productAmount + warehouse.getAmount(product);
                }
            }
            currentStateOfProducts.put(product, productAmount);
        }
        return currentStateOfProducts;
    }

    /**
     * Finds all warehouses where the product is available.
     *
     * @param product the product to check
     * @return list of warehouses where the product is availalble
     */
    public List<Warehouse> getAvailability(Product product) {
        List<Warehouse> listOfWarehousesWithProduct = new LinkedList<>();
        for (Warehouse warehouse : warehouses) {
            if (warehouse.hasProduct(product) && warehouse.getAmount(product) > 0) {
                listOfWarehousesWithProduct.add(warehouse);
            }
        }
        return listOfWarehousesWithProduct;
    }

    /**
     * Adds specified amount of specified product to all warehouses.
     *
     * @param product the product to add
     * @param amount  the amount of product to add
     */
    public void restockProduct(Product product, Long amount) {
        Map.Entry<Product, Long> entry = new AbstractMap.SimpleEntry<>(product, amount);
        for (Warehouse warehouse : warehouses) {
            warehouse.addProduct(entry);
        }
    }

    /**
     * Add a new product to companies products list and all the warehouses of that company.
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        restockProduct(product, (long) 0);
        products.add(product);
    }

    /**
     * Gets all company workers.
     *
     * @return all company workers
     */
    public Set<Worker> getCompanyWorkers() {
        Set<Worker> allWorkers = new HashSet<>(officeWorkers);
        for (Warehouse warehouse : warehouses) {
            allWorkers.addAll(warehouse.getWorkers());
        }
        return allWorkers;
    }

    /**
     * Add an office worker.
     *
     * @param worker the worker to add
     */
    public void addOfficeWorker(Worker worker) {
        officeWorkers.add(worker);
    }


    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    /**
     * Add a warehouse.
     *
     * @param wareHouse the warehouse to add
     */
    public void addWarehouse(Warehouse wareHouse) {
        warehouses.add(wareHouse);
    }

    /**
     * Gets total company goods value.
     *
     * @return total company goods value
     */
    public BigDecimal getCompanyGoodsValue() {
        BigDecimal goodsValue = new BigDecimal(0);
        for (Warehouse warehouse : warehouses) {
            goodsValue = goodsValue.add(warehouse.getInventoryValue());
        }
        return goodsValue;
    }
}
