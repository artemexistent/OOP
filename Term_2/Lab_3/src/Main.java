import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main implements Runnable{


    void main() {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        Tunnel tunnel = new Tunnel();

        ShipGenerator shipGenerator = new ShipGenerator(tunnel, 10);

        PierLoader pierLoader1 = new PierLoader(tunnel, Type.BANANAS);
        PierLoader pierLoader2 = new PierLoader(tunnel, Type.GARBAGE);
        PierLoader pierLoader3 = new PierLoader(tunnel, Type.MELONS);
        PierLoader pierLoader4 = new PierLoader(tunnel, Type.SANDS);
        PierLoader pierLoader5 = new PierLoader(tunnel, Type.STONES);

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(shipGenerator);
        service.execute(pierLoader1);
        service.execute(pierLoader2);
        service.execute(pierLoader3);
        service.execute(pierLoader4);
        service.execute(pierLoader5);

        service.shutdown();
    }

    @Override
    public void run() {
        main();
    }
}