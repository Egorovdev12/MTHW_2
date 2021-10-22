package hw1;

import java.util.LinkedList;
import java.util.List;

public class AutoShop {
    public Seller seller;
    public List<Auto> autoList;

    public AutoShop() {
        this.seller =  new Seller(this);
        this.autoList = new LinkedList<>();
    }

    public synchronized Auto sellAuto() {
        try {
            System.out.println("Магазин производит продажу товара: клиент: " + Thread.currentThread().getName());
            while (autoList.size() == 0) {
                System.out.println("На данный момент автомобилей в продаже нет. необходимо ожидать поставки");
                wait();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " получил свой автомобиль");
        }
        catch (java.lang.InterruptedException ex) {
            ex.printStackTrace();
        }
        return autoList.remove(0);
    }

    public synchronized void getNewAuto() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("Магазин получает новый автомобиль на продажу");
                Thread.sleep(2000);
                this.autoList.add(new Auto());
                System.out.println("В магазин поступил новый автомобиль. Количетсво автомобилей в продаже: " + autoList.size());
                notify();
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}