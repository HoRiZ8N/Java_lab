package Lab_3;

class Interval {
    private double start;
    private double end;
    private boolean startIncluded;
    private boolean endIncluded;

    public Interval(double start, double end, boolean startIncluded, boolean endIncluded) {
        if (start > end) {
            throw new IllegalArgumentException("Beginning of the interval cannot be greater than the end");
        }
        this.start = start;
        this.end = end;
        this.startIncluded = startIncluded;
        this.endIncluded = endIncluded;
    }

    public Interval(double start, double end) {
        this(start, end, true, true);
    }

    public double getStart() { return start; }
    public double getEnd() { return end; }
    public boolean isStartIncluded() { return startIncluded; }
    public boolean isEndIncluded() { return endIncluded; }

    public boolean contains(double point) {
        boolean afterStart = (point > start) || (point == start && startIncluded);
        boolean beforeEnd = (point < end) || (point == end && endIncluded);
        return afterStart && beforeEnd;
    }

    public boolean intersects(Interval other) {
        if (this.contains(other.start) || this.contains(other.end) ||
                other.contains(this.start) || other.contains(this.end)) {
            return true;
        }

        if (this.end == other.start) {
            return this.endIncluded && other.startIncluded;
        }
        if (this.start == other.end) {
            return this.startIncluded && other.endIncluded;
        }

        return false;
    }

    public Interval intersection(Interval other) {
        if (!this.intersects(other)) {
            return null; // Интервалы не пересекаются
        }

        double newStart, newEnd;
        boolean newStartIncluded, newEndIncluded;

        if (this.start > other.start) {
            newStart = this.start;
            newStartIncluded = this.startIncluded;
        } else if (this.start < other.start) {
            newStart = other.start;
            newStartIncluded = other.startIncluded;
        } else {
            newStart = this.start;
            newStartIncluded = this.startIncluded && other.startIncluded;
        }

        if (this.end < other.end) {
            newEnd = this.end;
            newEndIncluded = this.endIncluded;
        } else if (this.end > other.end) {
            newEnd = other.end;
            newEndIncluded = other.endIncluded;
        } else {
            newEnd = this.end;
            newEndIncluded = this.endIncluded && other.endIncluded;
        }

        return new Interval(newStart, newEnd, newStartIncluded, newEndIncluded);
    }

    public Interval union(Interval other) {
        if (!this.intersects(other)) {
            return null; // Интервалы не пересекаются, объединение невозможно
        }

        double newStart, newEnd;
        boolean newStartIncluded, newEndIncluded;

        if (this.start < other.start) {
            newStart = this.start;
            newStartIncluded = this.startIncluded;
        } else if (this.start > other.start) {
            newStart = other.start;
            newStartIncluded = other.startIncluded;
        } else {
            newStart = this.start;
            newStartIncluded = this.startIncluded || other.startIncluded;
        }

        if (this.end > other.end) {
            newEnd = this.end;
            newEndIncluded = this.endIncluded;
        } else if (this.end < other.end) {
            newEnd = other.end;
            newEndIncluded = other.endIncluded;
        } else {
            newEnd = this.end;
            newEndIncluded = this.endIncluded || other.endIncluded;
        }

        return new Interval(newStart, newEnd, newStartIncluded, newEndIncluded);
    }

    public double length() {
        return end - start;
    }

    @Override
    public String toString() {
        String leftBracket = startIncluded ? "[" : "(";
        String rightBracket = endIncluded ? "]" : ")";
        return leftBracket + start + ", " + end + rightBracket;
    }
}