package ru.java.task3.Terrain;

public enum Terrain {

    SWAMP("болото"),
    ROAD("дорога"),
    FOREST("лес"),
    DESERT("пустыня"),
    PLAIN("равнина");

    private String name;

    public String getName() {
        return name;
    }

    Terrain(String name) {
        this.name = name;
    }
}

