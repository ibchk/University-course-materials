public class ChocolateFactory {
    private int chocolateBoxesMade = 0;
    private int costSoFar = 0;

    public enum BoxType {

        SQUARE1(4, 4),
        SQUARE2(5, 5),
        RECTANGLE1(3, 6),
        RECTANGLE2(4, 8);

        private int width;
        private int length;

        BoxType(int width, int length) {
            this.width = width;
            this.length = length;
        }
    }

    public ChocolateType[][] makeChocolateBox(ChocolateType chocolate1, ChocolateType chocolate2,
                                              Integer preferedChocolate1Count, BoxType boxType) {
        ChocolateType[][] box = new ChocolateType[boxType.length][boxType.width];
        if (preferedChocolate1Count == 0) {
            for (int i = 0; i < boxType.length; i++) {
                for (int j = 0; j < boxType.width; j++) {
                    box[i][j] = chocolate2;
                }
            }
        } else if (preferedChocolate1Count == 4) {
            for (int i = 0; i < boxType.length; i++) {
                for (int j = 0; j < boxType.width; j++) {
                    if ((i == 0 || i == boxType.length - 1) && (j == 0 || j == boxType.width - 1)) {
                        box[i][j] = chocolate1;
                    } else box[i][j] = chocolate2;
                }
            }
        } else if (boxType.width * 2 + boxType.length * 2 - 4 == preferedChocolate1Count) {
            for (int i = 0; i < boxType.length; i++) {
                for (int j = 0; j < boxType.width; j++) {
                    if (i == 0 || i == boxType.length - 1 || j == 0 || j == boxType.width - 1) {
                        box[i][j] = chocolate1;
                    } else box[i][j] = chocolate2;
                }
            }
        } else {
            int nr = 1;
            for (int i = 0; i < boxType.length; i++) {
                for (int j = 0; j < boxType.width; j++) {
                    if (nr == 1) {
                        box[i][j] = chocolate1;
                    } else {
                        box[i][j] = chocolate2;
                    }
                    nr = (nr + 1) % 2;
                }
                nr = (nr + 1) % 2;
            }
        }
        for (int i = 0; i < boxType.length; i++) {
            for (int j = 0; j < boxType.width; j++) {
                costSoFar += box[i][j].price;
            }
        }
        chocolateBoxesMade++;
        return box;
    }

    public int getChocolateBoxesMade() {
        return chocolateBoxesMade;
    }

    public int getCostSoFar() {
        return costSoFar;
    }
}
