package task2;

public class Main {

    public static void main(String[] args) {
        Box box = new Box(500, "blue");
        Box box1 = new Box(250, "green");

        System.out.println("Коробка box: ");
        box.info();
        box.open();
        box.put(new Phone(120, 15));
        box.put(new Phone(180, 25));
        box.put(new Phone(195, 20));
        box.put(new Toy(300, 50));
        box.put(new Toy(400, 70));
        box.info();

        System.out.println("\nКоробка box1:");
        box1.info();
        box1.open();
        box1.put(new Phone(135, 30));
        box1.put(new Phone(210, 35));
        box1.put(new Phone(220, 40));
        box1.info();

        System.out.println("Можно ли положить коробку в коробку: " + box.isVolumeEnough(box1));
        box.put(box1);
        box.info();
        box.takeItemFromBox(box1);
        box.info();

        System.out.println("\nПроверка наличия предмета в коробке: " + box.isInBox(new Phone(120, 15)));  // наличие предмета в коробке ???

        System.out.println("\nКладём предметы из одной коробки в другую коробку: ");
        box.getItemsFromAnotherBox(box1);
        box.info();
        box1.info();
        System.out.println("Самый объемный предмет в коробке: " + box.getMaxVolumeItem());
        box.getMaxItemsName();
        System.out.println(box.compareTo(box1));
        box.clear();
        box.info();
        box.changeColor("red");
        box.info();


//        box.getItems().add(new Phone(120, 1000));

    }
}

