import java.util.LinkedList;
import java.util.List;

public class GameResult implements Result{
    private List<String> jumps = new LinkedList<>();
    private int totalFine = 0;

    public void setJumps(List<String> jumps) {
        this.jumps = jumps;
    }

    public void setTotalFine(int totalFine) {
        this.totalFine = totalFine;
    }

    @Override
    public List<String> getJumps() {
        return jumps;
    }

    @Override
    public int getTotalFine() {
        return totalFine;
    }
}
