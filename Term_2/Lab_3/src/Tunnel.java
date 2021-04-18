import java.util.ArrayList;
import java.util.List;

/**
 * a tunnel in which ships enter to wait in line
 */



public class Tunnel {

    private List<Ship> store;
    private static final int maxShipsInTunel = 5;
    private static final int minShipsInTunel = 0;
    private int shipsCounter = 0;

    public Tunnel() {
        store = new ArrayList<>();
    }

    /**
     * adding a new ship to the tunnel
     *
     * @param element
     * @return
     */

    public synchronized boolean add(Ship element) {

        try {
            if (shipsCounter < maxShipsInTunel) {
                notifyAll();
                store.add(element);
                String info = String.format("%s + The ship arrived in the tunnel: %s %s %s", store.size(), element.getType(), element.getSize(), Thread.currentThread().getName());
                System.out.println(info);
                shipsCounter++;

            } else {
                System.out.println(store.size() + "> There is no place for a ship in the tunnel: " + Thread.currentThread().getName());
                wait();
                return false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * queue advancement
     *
     * @param shipType
     * @return
     */
    public synchronized Ship get(Type shipType) {

        try {
            if (shipsCounter > minShipsInTunel) {
                notifyAll();
                for (Ship ship : store) {
                    if (ship.getType() == shipType) {
                        shipsCounter--;
                        System.out.println(store.size() + "- The ship is taken from the tunnel: " + Thread.currentThread().getName());
                        store.remove(ship);
                        return ship;
                    }
                }
            }

            System.out.println("0 < There are no ships in the tunnel");
            wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
