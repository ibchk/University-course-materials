package ee.taltech.iti0200.greenhouse.plantingstrategy;

import java.util.Map;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class PlantInRows implements PlantingStrategy {

    public List<Map.Entry<String, Integer>> sortMap(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()); // for example
            }
        }.reversed());
        return list;
    }

    @Override
    public String[][] plantPlants(int length, int width, Map<String, Integer> plants) {
        String[][] field = new String[length][width];
        List<Map.Entry<String, Integer>> listOfPlants = sortMap(plants);
        for (Map.Entry<String, Integer> plant : listOfPlants) {
            for (int start = 0; start < plant.getValue(); start++) {
                for (int a = 0; a < length; a++) {
                    for (int b = 0; b < width; b++) {
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
