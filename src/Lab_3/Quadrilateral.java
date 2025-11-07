package Lab_3;

class Quadrilateral {
    private Point a;
    private Point b;
    private Point c;
    private Point d;
    private double sideAB;
    private double sideBC;
    private double sideCD;
    private double sideDA;
    private double diagonalAC;
    private double diagonalBD;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        calculateSidesAndDiagonals();
    }

    public Quadrilateral(double x1, double y1, double x2, double y2,
                         double x3, double y3, double x4, double y4) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
        this.c = new Point(x3, y3);
        this.d = new Point(x4, y4);
        calculateSidesAndDiagonals();
    }

    private void calculateSidesAndDiagonals() {
        this.sideAB = a.distanceTo(b);
        this.sideBC = b.distanceTo(c);
        this.sideCD = c.distanceTo(d);
        this.sideDA = d.distanceTo(a);
        this.diagonalAC = a.distanceTo(c);
        this.diagonalBD = b.distanceTo(d);
    }

    public double getPerimeter() {
        return sideAB + sideBC + sideCD + sideDA;
    }

    public double getArea() {
        double area1 = triangleArea(a, b, c);
        double area2 = triangleArea(a, c, d);
        return area1 + area2;
    }

    private double triangleArea(Point p1, Point p2, Point p3) {
        double side1 = p1.distanceTo(p2);
        double side2 = p2.distanceTo(p3);
        double side3 = p3.distanceTo(p1);
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    private boolean areParallel(Vector v1, Vector v2) {
        double epsilon = 1e-10;
        // Векторы параллельны если их векторное произведение близко к 0
        double crossProduct = v1.dotProduct(new Vector(new Point(0, 0), new Point(v2.length(), 0)));
        return Math.abs(crossProduct) < epsilon;
    }

    public boolean isSquare() {
        double epsilon = 1e-10;

        boolean equalSides = Math.abs(sideAB - sideBC) < epsilon &&
                Math.abs(sideBC - sideCD) < epsilon &&
                Math.abs(sideCD - sideDA) < epsilon;

        if (!equalSides) return false;

        Vector ab = new Vector(a, b);
        Vector bc = new Vector(b, c);
        double angle = ab.angleWith(bc);

        return Math.abs(angle - Math.PI/2) < epsilon;
    }

    public boolean isRectangle() {
        double epsilon = 1e-10;

        boolean oppositeSidesEqual = Math.abs(sideAB - sideCD) < epsilon &&
                Math.abs(sideBC - sideDA) < epsilon;

        Vector ab = new Vector(a, b);
        Vector bc = new Vector(b, c);
        double angle = ab.angleWith(bc);

        return Math.abs(angle - Math.PI/2) < epsilon && oppositeSidesEqual;
    }

    // Проверка на ромб
    public boolean isRhombus() {
        double epsilon = 1e-10;

        // Все стороны равны
        boolean allSidesEqual = Math.abs(sideAB - sideBC) < epsilon &&
                Math.abs(sideBC - sideCD) < epsilon &&
                Math.abs(sideCD - sideDA) < epsilon;

        return Math.abs(diagonalAC - diagonalBD) > epsilon && allSidesEqual;
    }

    public boolean isParallelogram() {
        double epsilon = 1e-10;

        // Противоположные стороны равны
        boolean oppositeSidesEqual = Math.abs(sideAB - sideCD) < epsilon &&
                Math.abs(sideBC - sideDA) < epsilon;

        // Диагонали делятся пополам
        Point midAC = new Point((a.getX() + c.getX()) / 2, (a.getY() + c.getY()) / 2);
        Point midBD = new Point((b.getX() + d.getX()) / 2, (b.getY() + d.getY()) / 2);
        boolean diagonalsBisect = Math.abs(midAC.getX() - midBD.getX()) < epsilon &&
                Math.abs(midAC.getY() - midBD.getY()) < epsilon;

        return oppositeSidesEqual && diagonalsBisect;
    }

    // Получить тип четырехугольника
    public String getType() {
        if (isSquare()) {
            return "Квадрат";
        } else if (isRectangle()) {
            return "Прямоугольник";
        } else if (isRhombus()) {
            return "Ромб";
        } else if (isParallelogram()) {
            return "Параллелограмм";
        } else {
            return "Произвольный";
        }
    }

    @Override
    public String toString() {
        return String.format("Четырехугольник[%s, %s, %s, %s] (P=%.2f, S=%.2f)",
                a, b, c, d, getPerimeter(), getArea());
    }
}
