package ru.java.task3.Transport;

import ru.java.task3.Human;
import ru.java.task3.Terrain.Terrain;

public class Bicycle implements Transport {

    private Human driver;

    @Override
    public void setDriver(Human driver) {
        this.driver = driver;
    }

    @Override
    public Human getDriver() {
        return driver;
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        if (terrain == Terrain.SWAMP || terrain == Terrain.DESERT) {
            System.out.println("Велоcипед не может перемещаться по болоту пустыне");
            return false;
        }
        if (driver.wastePower(distance)) {
            System.out.println(getClass().getSimpleName() + " передвигается по " + terrain);
            return true;
        }
        System.out.println(driver + " не хватило сил");
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}