package lesson_2;

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
