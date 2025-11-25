package Lab_4.Task1;

abstract class Flower {
    private String name;
    private int stemLength;
    private double price;
    private int freshness;  // 1 — свежий, 10 — несвежий

    public Flower(String name, int stemLength, double price, int freshness) {
        this.name = name;
        this.stemLength = stemLength;
        this.price = price;
        this.freshness = freshness;
    }

    public String getName() { return name; }
    public int getStemLength() { return stemLength; }
    public double getPrice() { return price; }
    public int getFreshness() { return freshness; }

    @Override
    public String toString() {
        return name + " (длина: " + stemLength + " см, свежесть: " + freshness + ")";
    }
}

class Rose extends Flower {
    public Rose(int stemLength, double price, int freshness) {
        super("Роза", stemLength, price, freshness);
    }
}

class Tulip extends Flower {
    public Tulip(int stemLength, double price, int freshness) {
        super("Тюльпан", stemLength, price, freshness);
    }
}

class Chamomile extends Flower {
    public Chamomile(int stemLength, double price, int freshness) {
        super("Ромашка", stemLength, price, freshness);
    }
}