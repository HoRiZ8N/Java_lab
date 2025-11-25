package Lab_4.Task2;

public class Main {
    public static void main(String[] args) {
        GiftBox gift = new GiftBox();

        gift.addSweet(new Chocolate("Алёнка", 50, 35, "молочный"));
        gift.addSweet(new Candy("Красный мак", 15, 80, "карамель"));
        gift.addSweet(new Cookie("Юбилейное", 20, 25, false));
        gift.addSweet(new Chocolate("Россия", 40, 45, "горький"));
        gift.addSweet(new Candy("Птичье молоко", 25, 60, "сливочный"));

        System.out.printf("Общий вес подарка: %.1fг\n\n", gift.getTotalWeight());

        gift.sortBySugarContent();
        gift.printContents();

        System.out.println("\nКонфеты с содержанием сахара 30-50%:");
        for (Sweet s : gift.findSweetsBySugarRange(30, 50)) {
            System.out.println(s);
        }
    }
}
