package hw1;

import java.util.ArrayList;

class AutoShop {

    private ArrayList<Car> autoList = new ArrayList<>();
    private final int SLEEP_TIME = 1000;

    private class Car {

    }

    public synchronized Car sellCar() {
        while (autoList.isEmpty()) {
            try {
                System.out.println("Клиент:" + Thread.currentThread().getName() + " ожидает поставки");
                wait();
                Thread.sleep(SLEEP_TIME);
                System.out.println(Thread.currentThread().getName() + " получает свой новенький автомобиль");
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return autoList.remove(0);
    }

    public synchronized void putNewCar() {
        autoList.add(new Car());
        System.out.println("В автосалон поступает новый автомобиль");
        try {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Новый автомобиль доступен в продаже");
        notify();
    }
}