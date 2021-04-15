/**
 * Counting down the ship's dwell time
 */

public class PierLoader implements Runnable {

    private Type shipType;

    public PierLoader(Type shipType) {

        this.shipType =shipType;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.currentThread().setName("Loader "+shipType);

                //Time to load the goods
                Thread.sleep(500);
                Ship ship = ;
                if(ship!=null)
                    while (ship.countCheck()){
                        Thread.sleep(100);
                        ship.add(10);
                        System.out.println(ship.getCount() + " Loaded ship. " + Thread.currentThread().getName());
                    }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}