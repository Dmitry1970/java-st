package ru.java.task3.Transport;

import ru.java.task3.Human;
import ru.java.task3.Terrain.Terrain;

public class Horse implements Transport {

    private int power;
    private Human driver;

    public Horse(int power) {
        this.power = power;
        this.driver = null;
    }

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
        if (terrain == Terrain.SWAMP) {
            System.out.println("Лошадь не может перемещаться по болоту");
            return false;
        }
        if (power < distance) {
            System.out.println("Лошади не хватит сил на дистанцию");
            return false;
        }
        power -= distance;
        System.out.println("Лошадь пробежала " + distance + " метров по " + terrain.getName());
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {horsePower = " + power +
                "}";
    }
}
