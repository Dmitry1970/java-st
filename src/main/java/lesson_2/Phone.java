package lesson_2;

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
    public String toString() {
        return "Phone {" +
                "вес - " + weight +
                ", объём - " + volume +
                "}";
    }
}
