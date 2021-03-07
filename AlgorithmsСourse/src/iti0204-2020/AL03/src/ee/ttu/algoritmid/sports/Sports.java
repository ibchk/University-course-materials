import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


public class Sports {

    Map<String, String> answersByName = new HashMap<>();
    Map<String, Integer> answersCountBySport = new HashMap<>();

    /**
     * @param name - name of the answerer, sport - name of his favourite sport
     */
    void addAnswer(String name, String sport) {
        if (!answersByName.containsKey(name)) {
            answersByName.put(name, sport);
            answersCountBySport.merge(sport, 1, Integer::sum);
        }
    }

    /**
     * @param sport
     * @return the number different answerers, who consider the sport his favourite
     */
    int sportFanclubSize(String sport) {
        if (answersCountBySport.containsKey(sport)) {
            return answersCountBySport.get(sport);
        }
        return 0;
    }

    /**
     * @return the most popular sport
     */
    Optional<String> mostPopularSport() {
        String sport = "";
        int quantity = 0;
        for (Map.Entry<String, Integer> sportQuantity: answersCountBySport.entrySet()){
            if (sportQuantity.getValue() > quantity){
                quantity = sportQuantity.getValue();
                sport = sportQuantity.getKey();
            }
        }
        return (!sport.equals("")) ? Optional.of(sport): Optional.empty();
    }

    /**
     * @return the number of answerers
     */
    int numberOfAnswerers() {
        int quantity = 0;
        for (Map.Entry<String, Integer> sportQuantity: answersCountBySport.entrySet()){
            quantity += sportQuantity.getValue();
        }
        return quantity;
    }
}
