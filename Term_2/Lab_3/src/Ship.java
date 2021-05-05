/**
 * ship description class
 */

public class Ship {
    private int count;
    private int size;
    private Type type;

    public Ship(int size, Type type) {
        this.size = size;
        this.type = type;
    }

    public void add(int count) {
        this.count += count;
    }

    public boolean countCheck() {
        if (count >= size) {
            return false;
        }
        return true;
    }

    public int getCount() {
        return count;
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}


