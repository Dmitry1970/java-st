package ru.java.task3.Transport;

import ru.java.task3.Human;
import ru.java.task3.Terrain.Terrain;

public interface Transport {

    void setDriver(Human driver);

    Human getDriver();

    boolean move(Terrain terrain, int distance);


}
