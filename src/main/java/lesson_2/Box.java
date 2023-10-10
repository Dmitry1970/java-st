package lesson_2;

import java.util.ArrayList;
import java.util.List;

public class Box implements Comparable<Box>, Item {

    private int volume;
    private String color;
    private List<Item> items;
    private boolean isOpen;
    private int weight;

    private List<Item> getItems() {   //сделал private для недоступности из main
        return items;
    }

    public List<Item> getGetItems() {
        return getItems();
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getColor() {
        return color;
    }

    public Box(int volume, String color) {
        this.volume = volume;
        this.color = color;
        this.items = new ArrayList<>();
        this.weight = 0;
    }

    public boolean isInBox(Item item) {    // проверка наличия предмета в коробке
        return items.contains(item);
    }

    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Коробка открыта");
        } else {
            System.out.println("Коробка не требует отрытия");
        }
    }

    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Коробка закрыта");
        } else {
            System.out.println("Коробка не требует закрывания");
        }
        System.out.println("Коробка закрыта");
    }

    public void changeColor(String color) {
        this.color = color;
        System.out.printf("Коробку перекрасили в %s цвет\n", color);
    }

    public int getCurrentVolume() {
        int currentVolume = 0;
        for (int i = 0; i < items.size(); i++) {
            currentVolume += items.get(i).getVolume();
        }
        return currentVolume;
    }

    public int getCurrentWeight() {    // текущий вес всех предметов в коробке
        int itemsWeight = 0;
        for (int i = 0; i < items.size(); i++) {
            itemsWeight += items.get(i).getWeight();
        }
        return itemsWeight;
    }

    public void put(Item item) {
        if (!isOpen) {
            System.out.println("Коробка не открыта");
            return;
        }
        if (getCurrentVolume() + item.getVolume() > volume) {
            System.out.println("В коробке не хватает места");
            return;
        }
        items.add(item);
        weight += item.getWeight();
        System.out.printf("Положили %s в коробку\n", item);
    }

    public boolean isPutBoxInBox(Box b) {     // можно ли положить коробку в коробку
        return (this.volume - this.getCurrentVolume() > b.volume);
    }

    public void getItemsFromAnotherBox(Box box) {     // кладём предметы из другой коробки
        if (this.getCurrentVolume() - box.getCurrentVolume() > 0) {
            for (Item i : box.getItems()) {
                put(i);
            }
            getCurrentWeight();  // пересчитывает вес всех предметов в коробке после добавления предметов из коробки
            System.out.println("В коробке стало предметов: " + getClass().getSimpleName() + "= {" + this.getItems() + "}");
        }
    }

    public void takeItemFromBox(Item item) {
        System.out.println("Выкладываем " + item + " из коробки");
        items.remove(item);
    }

    public void clear() {
        if (!isOpen) {
            System.out.println("Коробка не открыта");
            return;
        }
        if (items.isEmpty()) {
            System.out.println("Коробка пустая");
            return;
        }
        System.out.printf("Из коробки выкинули %d предметов\n", items.size());
        items.clear();
    }

    public void info() {
        System.out.printf("Первоначальный объем коробки - %d, заполнено объёма - %d,  вес коробки - %d, цвет коробки- %s," +
                " коробка открыта - %b\n", volume, getCurrentVolume(), getCurrentWeight(), color, isOpen);
    }

    public Item getMaxVolumeItem() {
        int maxVolume = items.get(0).getVolume();
        Item it = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (maxVolume < items.get(i).getVolume()) {
                maxVolume = items.get(i).getVolume();
                it = items.get(i);
            }
        }
        return it;
    }

    public void getMaxItemsName() {
        int maxCountName = 0;
        String temp = "";
        String maxName = "";
        for (int i = 0; i < items.size(); i++) {
            temp = items.get(i).getClass().getSimpleName();
            int countTemp = 0;
            for (int j = 0; j < items.size(); j++) {
                if (temp.equals(items.get(j).getClass().getSimpleName())) {
                    countTemp++;
                }
            }
            if (maxCountName < countTemp) {
                maxCountName = countTemp;
                maxName = temp;
            }

        }
        System.out.printf("Больше всего в коробке предметов класса %s, количество предметов этого класса - %d\n", maxName, maxCountName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " = {" +
                "список предметов : \n" + getItems();
    }

    @Override
    public int compareTo(Box o) {
        if (this.weight > o.weight) {
            return 1;
        } else if (this.weight < o.weight) {
            return -1;
        }
        return 0;
    }
}
