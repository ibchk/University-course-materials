public class ChocolateType {
    int price;
    String string;

    public ChocolateType(int price, String string) {
        this.price = price;
        this.string = string;
    }

    public int getPricePerPiece() {
        return price;
    }

    public String getStringForm() {
        return string;
    }
}
