import java.util.Random;

public class ShipGenerator {

    private Type getRandomType() {
        Random random = new Random();
        return Type.values()[random.nextInt(Type.values().length)];
    }

    private int getRandomSize() {
        Random random = new Random();
        return random.nextInt();
    }
}
