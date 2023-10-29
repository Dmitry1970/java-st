package ru.java.task3;


import ru.java.task3.Terrain.Terrain;
import ru.java.task3.Transport.Transport;

import java.util.HashMap;
import java.util.Map;

public class Human {

    private String name;
    private int power;
    private Transport transport;
    private Map<Terrain, Integer> powerPerMeter;


    public Human(String name, int power) {
        this.name = name;
        this.power = power;
        this.powerPerMeter = new HashMap<>();
        powerPerMeter.put(Terrain.DESERT, 10);
        powerPerMeter.put(Terrain.SWAMP, 15);
        powerPerMeter.put(Terrain.FOREST, 4);
    }

    public void getInTransport(Transport transport) {
        if (this.transport != null) {
            System.out.println(name + " уже находится в транспорте " + this.transport);
            return;
        }
        if (transport.getDriver() != null) {
            System.out.println("Транспорт " + transport.getClass().getSimpleName() + " занят водителем " + transport.getDriver().name);
            return;
        }

        this.transport = transport;
        this.transport.setDriver(this);
        System.out.println(name + " сел в " + transport);
    }


    public void getOutTransport() {
        if (transport == null) {
            System.out.println(name + " не находится в " + transport);
            return;
        }
        System.out.println(name + " вылезает с " + transport);
        transport.setDriver(null);
        transport = null;
    }

    public boolean wastePower(int amount) {
        if (power < amount) {
            System.out.println("У человека нет столько сил");
            return false;
        }
        power -= amount;
        System.out.println("Человек потратил " + amount);
        return true;

    }

    public boolean move(Terrain terrain, int distance) {
        if (transport == null) {
            int powerRequirement = powerPerMeter.getOrDefault(terrain, 1) * distance;
            if (power < powerRequirement) {
                System.out.println("У человека не хватит сил, чтобы пройти " + distance);
                return false;
            }
            power -= powerRequirement;
            System.out.println(name + " идёт пешком по " + terrain.getName() + ". Запас сил - " + power);
            return true;
        }
        return transport.move(terrain, distance);
    }

    public void info() {
        System.out.println(getClass().getSimpleName() + "{имя - " + name + ". Запас сил - " + power + ". Находится в транспорте: " + transport + "}");
    }
}
