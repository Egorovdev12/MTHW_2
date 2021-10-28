package hw2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AdvancedAutoShop {

    private ArrayList<Car> autoList = new ArrayList<>();
    private final int SLEEP_TIME = 1000;
    private Lock locker = new ReentrantLock(true);
    private Condition generalCondition = locker.newCondition();

    private class Car {

    }

    public Car sellCar() {
        locker.lock();
        while (autoList.isEmpty()) {
            try {
                System.out.println("Клиент:" + Thread.currentThread().getName() + " ожидает поставки");
                generalCondition.await();
                Thread.sleep(SLEEP_TIME);
                System.out.println(Thread.currentThread().getName() + " получает свой новенький автомобиль");
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            finally {
                locker.unlock();
            }
        }
        return autoList.remove(0);
    }

    public void putNewCar() {
        locker.lock();
        try {
            autoList.add(new Car());
            System.out.println("В автосалон поступает новый автомобиль");
            Thread.sleep(SLEEP_TIME);
            System.out.println("Новый автомобиль доступен в продаже");
            generalCondition.signal();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            locker.unlock();
        }
    }
}