package Lab_4.Task1;

public class Main {
    public static void main(String[] args) {
        Bouquet bouquet = new Bouquet();

        bouquet.addFlower(new Rose(40, 150, 2));
        bouquet.addFlower(new Rose(45, 170, 5));
        bouquet.addFlower(new Tulip(30, 70, 1));
        bouquet.addFlower(new Chamomile(25, 50, 7));

        bouquet.addAccessory(new Accessory("Лента", 20));
        bouquet.addAccessory(new Accessory("Упаковочная бумага", 30));

        System.out.println("Стоимость букета: " + bouquet.getPrice() + " руб.");

        System.out.println("\nСортировка по свежести:");
        bouquet.sortByFreshness();
        System.out.println(bouquet);

        System.out.println("\nЦветы с длиной стебля от 30 до 45 см:");
        for (Flower f : bouquet.findByStemLength(30, 45)) {
            System.out.println(f);
        }
    }
}
