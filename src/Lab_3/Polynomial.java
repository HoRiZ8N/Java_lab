package Lab_3;

import java.util.Arrays;

public class Polynomial {
    private final double[] coefficients;
    private int degree;

    public Polynomial(double[] coefficients) {
        if (coefficients == null || coefficients.length == 0) {
            throw new IllegalArgumentException("Coefficients array cannot be empty");
        }
        this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
        this.degree = calculateDegree();
    }

    private int calculateDegree() {
        int deg = coefficients.length - 1;
        while (deg > 0 && coefficients[deg] == 0) {
            deg--;
        }
        return deg;
    }

    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double a = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double b = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            result[i] = a + b;
        }
        return new Polynomial(result);
    }

    public Polynomial subtract(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double a = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double b = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            result[i] = a - b;
        }
        return new Polynomial(result);
    }

    public Polynomial multiply(Polynomial other) {
        double[] result = new double[this.coefficients.length + other.coefficients.length - 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                result[i + j] += this.coefficients[i] * other.coefficients[j];
            }
        }
        return new Polynomial(result);
    }

    public Polynomial[] divide(Polynomial divisor) {
        if (divisor.degree == 0 && divisor.coefficients[0] == 0) {
            throw new ArithmeticException("Division by zero polynomial");
        }

        double[] quotient = new double[Math.max(0, this.degree - divisor.degree + 1)];
        double[] remainder = Arrays.copyOf(this.coefficients, this.coefficients.length);

        for (int i = remainder.length - 1; i >= divisor.degree; i--) {
            if (remainder[i] != 0) {
                double factor = remainder[i] / divisor.coefficients[divisor.degree];
                quotient[i - divisor.degree] = factor;

                for (int j = 0; j <= divisor.degree; j++) {
                    remainder[i - j] -= factor * divisor.coefficients[divisor.degree - j];
                }
            }
        }
        return new Polynomial[]{
                new Polynomial(quotient),
                new Polynomial(remainder).trim()
        };
    }

    private Polynomial trim() {
        return new Polynomial(Arrays.copyOf(coefficients, degree + 1));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;

        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (!firstTerm) {
                    sb.append(coefficients[i] > 0 ? " + " : " - ");
                } else if (coefficients[i] < 0) {
                    sb.append("-");
                }

                double absVal = Math.abs(coefficients[i]);
                if (i == 0) {
                    sb.append(absVal);
                } else {
                    if (absVal != 1) sb.append(absVal);
                    sb.append("x");
                    if (i > 1) sb.append("^").append(i);
                }
                firstTerm = false;
            }
        }
        return sb.isEmpty() ? "0" : sb.toString();
    }
}
