package ru.java.task3;

import ru.java.task3.Terrain.Terrain;
import ru.java.task3.Transport.*;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(50);
        AllTerrainVechicle allTerrainVechicle = new AllTerrainVechicle(60);
        Bike bike = new Bike(30);
        Horse horse = new Horse(200);
        Human human = new Human("Василий", 100);
        Human human2 = new Human("Иван", 80);
        Bicycle bicycle = new Bicycle();

        human.info();
        human.getInTransport(car);
        human2.getInTransport(car);
        human.info();
        human2.info();
        human.getInTransport(car);
        System.out.println(human.move(Terrain.PLAIN, 10));
        human.getInTransport(bike);
        human2.getInTransport(bike);
        human2.info();
        human2.getInTransport(bike);
        human2.move(Terrain.PLAIN, 10);
        human2.getInTransport(car);
        human.getInTransport(allTerrainVechicle);
        human.getInTransport(bicycle);
        human.getOutTransport();
        human.info();
        human.getInTransport(horse);
        human.move(Terrain.DESERT, 80);
        human.info();
        human.getOutTransport();
        human.getInTransport(bicycle);
        human.move(Terrain.ROAD, 20);
        human.info();
    }
}
