package Lab_3;

public class QuadraticEquation {
    private final double a;
    private final double b;
    private final double c;

    // Конструктор
    public QuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient a cannot be zero");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double[] findRoots() {
        double discriminant = getDiscriminant();

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{root1, root2};
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return new double[]{root};
        } else {
            return new double[0];
        }
    }

    public double[] findExtremum() {
        double x = -b / (2 * a);
        double y = a * x * x + b * x + c;
        return new double[]{x, y};
    }

    public String getMonotonicityIntervals() {
        double vertexX = -b / (2 * a);

        if (a > 0) {
            return String.format("Убывает на (-∞, %.2f], возрастает на [%.2f, +∞)", vertexX, vertexX);
        } else {
            return String.format("Возрастает на (-∞, %.2f], убывает на [%.2f, +∞)", vertexX, vertexX);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Коэффициент a
        if (a != 0) {
            if (a == 1) {
                sb.append("x²");
            } else if (a == -1) {
                sb.append("-x²");
            } else {
                sb.append(String.format("%.2fx²", a));
            }
        }

        // Коэффициент b
        if (b != 0) {
            if (b > 0 && !sb.isEmpty()) sb.append(" + ");
            if (b < 0 && !sb.isEmpty()) sb.append(" - ");

            if (b == 1 && sb.isEmpty()) sb.append("x");
            else if (b == -1 && sb.isEmpty()) sb.append("-x");
            else if (b == 1) sb.append("x");
            else if (b == -1) sb.append("x");
            else if (Math.abs(b) != 1) {
                if (sb.isEmpty()) {
                    sb.append(String.format("%.2fx", b));
                } else {
                    sb.append(String.format("%.2fx", Math.abs(b)));
                }
            } else {
                sb.append("x");
            }
        }

        // Коэффициент c
        if (c != 0) {
            if (c > 0 && !sb.isEmpty()) sb.append(" + ");
            if (c < 0 && !sb.isEmpty()) sb.append(" - ");
            sb.append(String.format("%.2f", Math.abs(c)));
        }

        if (sb.isEmpty()) sb.append("0");
        sb.append(" = 0");

        return sb.toString();
    }
}
