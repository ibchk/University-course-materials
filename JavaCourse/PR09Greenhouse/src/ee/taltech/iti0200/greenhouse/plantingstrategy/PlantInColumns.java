package ee.taltech.iti0200.greenhouse.plantingstrategy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlantInColumns implements PlantingStrategy {

    public List<Map.Entry<String, Integer>> sortMap(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()); // for example
            }
        });
        return list;
    }

    @Override
    public String[][] plantPlants(int length, int width, Map<String, Integer> plants) {
        String[][] field = new String[length][width];
        List<Map.Entry<String, Integer>> listOfPlants = sortMap(plants);
        for (Map.Entry<String, Integer> plant : listOfPlants) {
            for (int start = 0; start < plant.getValue(); start++) {
                for (int b = 0; b < width; b++) {
                    for (int a = 0; a < length; a++) {
                        if (plant.getValue() != 0) {
                            if (field[a][b] == null) {
                                field[a][b] = plant.getKey();
                                plant.setValue(plant.getValue() - 1);
                            }
                        }
                    }
                }
            }
        }
        return field;
    }
}
