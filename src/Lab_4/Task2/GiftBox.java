package Lab_4.Task2;

import java.util.*;

class GiftBox {
    private final List<Sweet> sweets = new ArrayList<>();

    public void addSweet(Sweet sweet) {
        sweets.add(sweet);
    }

    public double getTotalWeight() {
        return sweets.stream().mapToDouble(Sweet::getWeight).sum();
    }

    public void sortBySugarContent() {
        sweets.sort(Comparator.comparingDouble(Sweet::getSugarContent));
    }

    public List<Sweet> findSweetsBySugarRange(double minSugar, double maxSugar) {
        List<Sweet> result = new ArrayList<>();
        for (Sweet s : sweets) {
            if (s.getSugarContent() >= minSugar && s.getSugarContent() <= maxSugar) {
                result.add(s);
            }
        }
        return result;
    }

    public void printContents() {
        System.out.println("Содержимое подарка:");
        sweets.forEach(System.out::println);
    }
}
