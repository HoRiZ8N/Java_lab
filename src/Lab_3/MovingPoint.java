package Lab_3;

class MovingPoint {
    private double x, y, z; // координаты
    private double time;     // время
    private double vx, vy, vz; // компоненты скорости
    private double ax, ay, az; // компоненты ускорения

    // Конструктор для точки на плоскости (z = 0)
    public MovingPoint(double x, double y, double time, double vx, double vy, double ax, double ay) {
        this(x, y, 0, time, vx, vy, 0, ax, ay, 0);
    }

    // Конструктор для точки в пространстве
    public MovingPoint(double x, double y, double z, double time, double vx, double vy, double vz, double ax, double ay, double az) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.ax = ax;
        this.ay = ay;
        this.az = az;
    }

    public MovingPoint getPositionAtTime(double targetTime) {
        double deltaTime = targetTime - this.time;
        double newX = x + vx * deltaTime + 0.5 * ax * deltaTime * deltaTime;
        double newY = y + vy * deltaTime + 0.5 * ay * deltaTime * deltaTime;
        double newZ = z + vz * deltaTime + 0.5 * az * deltaTime * deltaTime;

        return new MovingPoint(newX, newY, newZ, targetTime, vx, vy, vz, ax, ay, az);
    }

    public double[] getVelocityAtTime(double targetTime) {
        double deltaTime = targetTime - this.time;
        double currentVx = vx + ax * deltaTime;
        double currentVy = vy + ay * deltaTime;
        double currentVz = vz + az * deltaTime;

        return new double[]{currentVx, currentVy, currentVz};
    }

    public double getSpeedLen(double targetTime) {
        double[] velocity = getVelocityAtTime(targetTime);
        return Math.sqrt(velocity[0] * velocity[0] + velocity[1] * velocity[1] + velocity[2] * velocity[2]);
    }

    public double[] getAcceleration() {
        return new double[]{ax, ay, az};
    }

    public double getAccelerationLen() {
        return Math.sqrt(ax * ax + ay * ay + az * az);
    }

    public double distanceTo(MovingPoint other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public boolean checkTrajectoryIntersection(MovingPoint other, double maxTime, double epsilon) {
        for (double t = 0; t <= maxTime; t += 0.1) {
            MovingPoint p1 = this.getPositionAtTime(t);
            MovingPoint p2 = other.getPositionAtTime(t);

            if (p1.distanceTo(p2) < epsilon) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Point(%.1f, %.1f, %.1f), v=(%.1f, %.1f, %.1f), a=(%.1f, %.1f, %.1f)",
                x, y, z, vx, vy, vz, ax, ay, az);
    }
}