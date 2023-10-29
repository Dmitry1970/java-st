package ru.java.task3.Transport;

import ru.java.task3.Human;
import ru.java.task3.Terrain.Terrain;

public class AllTerrainVechicle implements Transport {


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

    public AllTerrainVechicle(int currentFuel) {
        this.currentFuel = currentFuel;
    }

    @Override
    public boolean move(Terrain terrain, int distance) {

        if (currentFuel < distance * FUEL_CONSUMPTION) {
            System.out.println("Вездеход не хватит топлива на дистанцию");
            return false;
        }
        currentFuel -= distance * FUEL_CONSUMPTION;
        System.out.println("Вездеход проехал " + distance + " метров");
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name = " + getClass().getSimpleName() +
                "}";
    }
}
