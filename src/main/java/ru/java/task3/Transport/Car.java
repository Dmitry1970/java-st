package ru.java.task3.Transport;

import ru.java.task3.Human;
import ru.java.task3.Terrain.Terrain;

public class Car implements Transport {


    private static final int FUEL_CONSUMPTION = 2;
    private int currentFuel;
    private Human driver;

    @Override
    public void setDriver(Human driver) {
        this.driver = driver;
    }

    @Override
    public Human getDriver() {
        return driver;
    }

    public Car(int currentFuel) {
        this.currentFuel = currentFuel;
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        if (terrain == Terrain.SWAMP) {
            System.out.println("Машина не может перемещаться по болоту");
            return false;
        }
        if (currentFuel < distance * FUEL_CONSUMPTION) {
            System.out.println("Машине не хватит топлива на дистанцию");
            return false;
        }
        currentFuel -= distance * FUEL_CONSUMPTION;
        System.out.println(getClass().getSimpleName() + " проехала " + distance + " метров по " + terrain.getName() + ", уровень топлива " + currentFuel);
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "уровень топлива: " + currentFuel +
                ", водитель:  " + driver.getClass().getSimpleName() +
                "}";
    }
}
