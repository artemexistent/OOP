/**
 * ship type
 */
public enum Type {
    BANANAS(1), SANDS(2), MELONS(3), STONES(4), GARBAGE(5);

    private final int value;

    private Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}