package task2;

import java.util.Objects;

public class Toy implements Item {

    private int weight;
    private int volume;

    public Toy(int weight, int volume) {
        this.weight = weight;
        this.volume = volume;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return weight == toy.weight && volume == toy.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, volume);
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String toString() {
        return "Toy {" +
                "вес - " + weight +
                ", объем - " + volume +
                "}";
    }
}
