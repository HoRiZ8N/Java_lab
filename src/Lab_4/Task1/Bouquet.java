package Lab_4.Task1;

import java.util.*;

class Bouquet {
    private final List<Flower> flowers = new ArrayList<>();
    private final List<Accessory> accessories = new ArrayList<>();
    private double price = 0;


    public void addFlower(Flower flower) {
        flowers.add(flower);
        this.price += flower.getPrice();
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
        this.price += accessory.getPrice();
    }

    public double getPrice() {
        return price;
    }

    public void sortByFreshness() {
        flowers.sort(Comparator.comparingInt(Flower::getFreshness));
    }

    public List<Flower> findByStemLength(int min, int max) {
        List<Flower> result = new ArrayList<>();
        for (Flower f : flowers) {
            if (f.getStemLength() >= min && f.getStemLength() <= max) {
                result.add(f);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Букет:\n");

        sb.append("Цветы:\n");
        for (Flower f : flowers) {
            sb.append("  - ").append(f).append("\n");
        }

        sb.append("Аксессуары:\n");
        for (Accessory a : accessories) {
            sb.append("  - ").append(a).append("\n");
        }

        sb.append("Общая стоимость: ").append(getPrice()).append(" руб.");

        return sb.toString();
    }
}
