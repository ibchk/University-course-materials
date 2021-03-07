import java.util.ArrayList;
import java.util.List;

public class JumpedTrampoline {

    private List<String> road = new ArrayList<>();
    private Integer totalFine = 0;
    private Trampoline next;

    public void setNext(Trampoline next) {
        this.next = next;
    }

    public Trampoline getNext() {
        return next;
    }

    public void setRoad(List<String> road) {
        this.road = road;
    }

    public void setTotalFine(Integer totalFine) {
        this.totalFine = totalFine;
    }

    public List<String> getRoad() {
        return road;
    }

    public Integer getTotalFine() {
        return totalFine;
    }
}
