package ee.taltech.iti0200.greenhouse.gardener;

import ee.taltech.iti0200.greenhouse.plantingstrategy.PlantingStrategy;

import java.util.Map;

public class Gardener {

    String clara;
    PlantingStrategy strategy1;

    public Gardener(String clara, PlantingStrategy strategy1) {
        this.clara = clara;
        this.strategy1 = strategy1;
    }

    public String[][] plantPlants(int i, int i1, Map<String, Integer> plants) {
        return strategy1.plantPlants(i, i1, plants);
    }
}
