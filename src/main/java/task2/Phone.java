package task2;

import java.util.Objects;

public class Phone implements Item {

    private int weight;
    private int volume;

    public Phone(int weight, int volume) {
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

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return weight == phone.weight && volume == phone.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, volume);
    }

    @Override
    public String toString() {
        return "Phone {" +
                "вес - " + weight +
                ", объём - " + volume +
                "}";
    }
}
