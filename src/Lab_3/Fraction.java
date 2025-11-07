package Lab_3;

public class Fraction {
    private int m;
    private int n;

    public Fraction(int m, int n) {
        if (n == 0) {
            throw new IllegalArgumentException("divisor cannot be equal to 0");
        }
        this.m = m;
        this.n = n;
        normalize();
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private void normalize() {
        int gcd = gcd(m, n);
        m /= gcd;
        n /= gcd;

        if (n < 0) {
            m = -m;
            n = -n;
        }
    }

    public Fraction add(Fraction other) {
        int newM = this.m * other.n + other.m * this.n;
        int newN = this.n * other.n;
        return new Fraction(newM, newN);
    }

    public Fraction subtract(Fraction other) {
        int newM = this.m * other.n - other.m * this.n;
        int newN = this.n * other.n;
        return new Fraction(newM, newN);
    }

    public Fraction multiply(Fraction other) {
        int newM = this.m * other.m;
        int newN = this.n * other.n;
        return new Fraction(newM, newN);
    }

    public Fraction divide(Fraction other) {
        if (other.m == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        int newM = this.m * other.n;
        int newN = this.n * other.m;
        return new Fraction(newM, newN);
    }

    public int getM() { return m; }
    public int getN() { return n; }

    public void setM(int m) { this.m = m; }
    public void setN(int n) { this.n = n; }

    @Override
    public String toString() {
        return m + "/" + n;
    }
}
