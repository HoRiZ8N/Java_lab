package Lab_4.Task2;

abstract class Sweet {
    private final String name;
    private final double weight;
    private final double sugarContent;

    public Sweet(String name, double weight, double sugarContent) {
        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public double getSugarContent() { return sugarContent; }

    @Override
    public String toString() {
        return String.format("%s (Вес: %.1fг, Сахар: %.1f%%)", name, weight, sugarContent);
    }
}


class Chocolate extends Sweet {
    private final String type;

    public Chocolate(String name, double weight, double sugarContent, String type) {
        super(name, weight, sugarContent);
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + " Тип: " + type;
    }
}

class Candy extends Sweet {
    private final String flavor;

    public Candy(String name, double weight, double sugarContent, String flavor) {
        super(name, weight, sugarContent);
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return super.toString() + " Вкус: " + flavor;
    }
}

class Cookie extends Sweet {
    private final boolean hasGluten;

    public Cookie(String name, double weight, double sugarContent, boolean hasGluten) {
        super(name, weight, sugarContent);
        this.hasGluten = hasGluten;
    }

    @Override
    public String toString() {
        return super.toString() + " Глютен: " + (hasGluten ? "да" : "нет");
    }
}