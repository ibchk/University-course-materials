import java.util.*;

public class HW02 implements TrampolineCenter {

    private Trampoline[][] map;
    private JumpedTrampoline[][] jumpedMap;
    private Map<Trampoline, Trampoline> neighbours;


    @Override
    public Result play(Trampoline[][] map) {
        GameResult newResult = new GameResult();
        this.map = map;
        jumpedMap = new JumpedTrampoline[map.length][map[0].length];
        neighbours = new LinkedHashMap<>();
        JumpedTrampoline trampoline = findAll(0, 0, Integer.MAX_VALUE);
        newResult.setJumps(trampoline.getRoad());
        newResult.setTotalFine(trampoline.getTotalFine());
        return newResult;
    }

    private JumpedTrampoline findAll(int row, int column, int maxJumps) {
        JumpedTrampoline next = null;
        int limit = maxJumps;
        Trampoline bestWay = null;
        int jumps = 0;
        String direction = "";
        int jumpForce = map[row][column].getJumpForce();
        jumpedMap[row][column] = new JumpedTrampoline();
        if (jumpForce == 0 ) {
            return jumpedMap[row][column];
        }
        for (int i = 1; i > -2; i--){
            int newRow = row + jumpForce + i;
            if (jumpForce + i > 0 && map.length > newRow && map[0].length > column && !neighbours.containsKey(map[newRow][column])) {
                JumpedTrampoline nextTrampoline = findAll(newRow, column, limit);
                if (nextTrampoline != null && (bestWay == null || jumps > nextTrampoline.getRoad().size())){
                    jumps = nextTrampoline.getRoad().size();
                    bestWay = map[newRow][column];
                    direction = "S" + (jumpForce + i);
                    next = nextTrampoline;
                }
            }
            int newColumn = column + jumpForce + i;
            if (jumpForce + i > 0 && map.length > row && map[0].length > newColumn && !neighbours.containsKey(map[row][newColumn])) {
                JumpedTrampoline nextTrampoline = findAll(row, newColumn, limit);
                if (nextTrampoline != null && (bestWay == null || jumps > nextTrampoline.getRoad().size())){
                    jumps = nextTrampoline.getRoad().size();
                    bestWay = map[row][newColumn];
                    direction = "E" + (jumpForce + i);
                    next = nextTrampoline;
                }
            }
        }
        if (bestWay == null){
            return null;
        }
        ArrayList<String> road = new ArrayList<>(Collections.singletonList(direction));
        road.addAll(next.getRoad());
        jumpedMap[row][column].setRoad(road);
        neighbours.put(map[row][column], bestWay);
        if (map[row][column].getType() == Trampoline.Type.WITH_FINE){
            jumpedMap[row][column].setTotalFine(next.getTotalFine() + jumpForce);
        } else {
            jumpedMap[row][column].setTotalFine(next.getTotalFine());
        }
        return jumpedMap[row][column];
    }


//    private Result playGame(int row, int column) {
//        GameResult result = new GameResult();
//        if (map.length <= row || map[0].length <= column) {
//            System.out.println("left: " + column + ", top: " + row + "back");
//            return null;
//        }
//        int jumpForce = map[row][column].getJumpForce();
//        if (jumpForce == 0) {
//            return result;
//        }
//        System.out.println("left: " + column + ", top: " + row + ", jump: " + jumpForce);
//        String direction = null;
//        Result jump = null;
//        for (int i = -1; i < 2; i++) {
//            int add = jumpForce + i;
//            if (add > 0) {
//                Result southJump = playGame(row + add, column);
//                if (southJump != null && (jump == null || southJump.getJumps().size() < jump.getJumps().size())) {
//                    jump = southJump;
//                    direction = "S";
//                }
//                Result eastJump = playGame(row, column + add);
//                if (eastJump != null && (jump == null || eastJump.getJumps().size() < jump.getJumps().size())) {
//                    jump = eastJump;
//                    direction = "E";
//                }
//            }
//        }
//        System.out.println("now:" + direction + jumpForce);
//        if (jump != null) {
//            List<String> jumps = new LinkedList<>(Collections.singletonList(direction + jumpForce));
//            jumps.addAll(jump.getJumps());
//            result.setJumps(jumps);
////            if (map[row][column].getType().equals(Trampoline.Type.WITH_FINE)) {
////                result.setTotalFine(jumpForce + jump.getTotalFine());
////            }
//            return result;
//        }
//        return null;
//    }

    public static void main(String[] args) {

    }
}