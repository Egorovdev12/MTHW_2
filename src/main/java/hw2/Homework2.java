package hw2;

public class Homework2 {

    private final static int PRODUCTION_TIME = 3000;
    static public final AdvancedAutoShop queue = new AdvancedAutoShop();

    public static void main(String[] args) {

        new Thread(null, queue::sellCar, "Покупатель-1").start();
        new Thread(null, queue::sellCar, "Покупатель-2").start();
        new Thread(null, queue::sellCar, "Покупатель-3").start();
        new Thread(null, queue::sellCar, "Покупатель-4").start();
        new Thread(null, queue::sellCar, "Покупатель-5").start();
        new Thread(null, queue::sellCar, "Покупатель-6").start();
        new Thread(null, queue::sellCar, "Покупатель-7").start();
        new Thread(null, queue::sellCar, "Покупатель-8").start();
        new Thread(null, queue::sellCar, "Покупатель-9").start();
        new Thread(null, queue::sellCar, "Покупатель-10").start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(PRODUCTION_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.putNewCar();
        }
    }
}