public class Number implements MagicNumber {
    private short nr;
    private boolean canBeSubtracted;
    private boolean canBePlacedNextToOther;

    public Number(short nr, boolean canBeSubtracted, boolean canBePlacedNextToOther) {
        this.nr = nr;
        this.canBeSubtracted = canBeSubtracted;
        this.canBePlacedNextToOther = canBePlacedNextToOther;
    }

    @Override
    public void setCanBeSubtracted(boolean canBeSubtracted) {
        this.canBeSubtracted = canBeSubtracted;
    }

    @Override
    public void setCanBePlacedNextToOther(boolean canBePlacedNextToOther) {
        this.canBePlacedNextToOther = canBePlacedNextToOther;
    }

    @Override
    public short getNumber() {
        return nr;
    }

    @Override
    public boolean canBeSubtracted() {
        return canBeSubtracted;
    }

    @Override
    public boolean canBePlacedNextToOther() {
        return canBePlacedNextToOther;
    }
}
