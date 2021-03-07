package ee.taltech.iti0200.greenhouse.plantingstrategy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlantInTurns implements PlantingStrategy {

    public List<String> sortMap(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()); // for example
            }
        }.reversed());
        List<String> listWithPlants = new LinkedList<>();
        int place = 0;
        while (list.get(0).getValue() > 0) {
            if (list.get(place).getValue() > 0) {
                listWithPlants.add(list.get(place).getKey());
                list.get(place).setValue(list.get(place).getValue() - 1);
            }
            place++;
            if (place == list.size()) {
                place = 0;
            }
        }
        System.out.println(listWithPlants);
        return listWithPlants;
    }

    @Override
    public String[][] plantPlants(int length, int width, Map<String, Integer> plants) {
        String[][] field = new String[length][width];
        List<String> listOfPlants = sortMap(plants);
        int a = 0;
        int b = 0;
        for (String plant : listOfPlants) {
            field[a][b] = plant;
            b++;
            if (b == width) {
                b = 0;
                a++;
            }
            if (a == length) {
                break;
            }
        }
        return field;
    }
}
