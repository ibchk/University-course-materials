public interface MagicNumber {

    /** Gets the number as short
     *
     * @return number
     */
    short getNumber();

    /**
     * For example if 3 cannot be subtrackted 1 + 2 - 3 is not allowed
     *
     * @return true, if number can have "-" in front of it
     */
    boolean canBeSubtracted();

    /**
     * For example if 2 cannot be placed next to other, then +-12 nor +-23 are allowed
     *
     * @return true, if number can be stacked with other number (Like 12)
     */
    boolean canBePlacedNextToOther();

    void setCanBeSubtracted(boolean canBeSubtracted);

    void setCanBePlacedNextToOther(boolean canBePlacedNextToOther);
}
