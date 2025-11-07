package Lab_3;

class Vector {
    private double x;
    private double y;

    public Vector(Point from, Point to) {
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    public double dotProduct(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double angleWith(Vector other) {
        double dot = dotProduct(other);
        double lengths = length() * other.length();
        return Math.acos(dot / lengths);
    }
}
