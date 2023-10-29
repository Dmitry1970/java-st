package ru.java.task3.Transport;


import ru.java.task3.Human;
import ru.java.task3.Terrain.Terrain;

public class Bike implements Transport {

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


    public Bike(int currentFuel) {
        this.currentFuel = currentFuel;
    }


    @Override
    public boolean move(Terrain terrain, int distance) {
        if (terrain == Terrain.SWAMP || terrain == Terrain.DESERT) {
            System.out.println("Мотоцикл не может перемещаться по болоту пустыне");
            return false;
        }
        if (currentFuel < distance * FUEL_CONSUMPTION) {
            System.out.println("Мотоцикл не хватит топлива на дистанцию");
            return false;
        }
        currentFuel -= distance * FUEL_CONSUMPTION;
        System.out.println(getClass().getSimpleName() + " проехал " + distance + " метров. Топлива в баке - " + currentFuel);
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "уровень топлива = " + currentFuel +
                ", водитель = " + getDriver().getClass().getSimpleName() +
                "}";
    }
}

