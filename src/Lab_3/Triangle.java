package Lab_3;

import java.util.*;

class Triangle {
    private Point a;
    private Point b;
    private Point c;
    private double sideAB;
    private double sideBC;
    private double sideCA;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        calculateSides();
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
        this.c = new Point(x3, y3);
        calculateSides();
    }

    private void calculateSides() {
        this.sideAB = a.distanceTo(b);
        this.sideBC = b.distanceTo(c);
        this.sideCA = c.distanceTo(a);
    }

    public double getPerimeter() {
        return sideAB + sideBC + sideCA;
    }

    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sideAB) * (p - sideBC) * (p - sideCA));
    }

    // Проверка на равносторонний треугольник
    public boolean isEquilateral() {
        double epsilon = 1e-10;
        return Math.abs(sideAB - sideBC) < epsilon &&
                Math.abs(sideBC - sideCA) < epsilon;
    }

    // Проверка на равнобедренный треугольник
    public boolean isIsosceles() {
        double epsilon = 1e-10;
        return Math.abs(sideAB - sideBC) < epsilon ||
                Math.abs(sideBC - sideCA) < epsilon ||
                Math.abs(sideCA - sideAB) < epsilon;
    }

    // Проверка на прямоугольный треугольник (теорема Пифагора)
    public boolean isRight() {
        double epsilon = 1e-10;
        double[] sides = {sideAB, sideBC, sideCA};
        Arrays.sort(sides);

        return Math.abs(Math.pow(sides[0], 2) + Math.pow(sides[1], 2) - Math.pow(sides[2], 2)) < epsilon;
    }

    public String getType() {
        if (isEquilateral()) {
            return "Равносторонний";
        } else if (isRight()) {
            return "Прямоугольный";
        } else if (isIsosceles()) {
            return "Равнобедренный";
        } else {
            return "Произвольный";
        }
    }

    @Override
    public String toString() {
        return String.format("Треугольник[%s, %s, %s] (P=%.2f, S=%.2f)",
                a, b, c, getPerimeter(), getArea());
    }
}