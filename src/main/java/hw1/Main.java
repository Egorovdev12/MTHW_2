package hw1;

public class Main {

    public static void main(String[] args) {

        AutoShop autoShop = new AutoShop();

        new Thread(null, autoShop::sellAuto, "Покупатель-1").start();
        new Thread(null, autoShop::sellAuto, "Покупатель-2").start();
        new Thread(null, autoShop::sellAuto, "Покупатель-3").start();
        new Thread(null, autoShop::getNewAuto, "Поставщик").start();


    }
}