package Lab_3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
//        fractionTask();
//        complexTask();
//        quadraticEquationTask();
//        polynomialTask();
//        pointTask();
//        intervalTask();
//        triangleTask();
//        quadrilateralTask();
//        circleTask();
//        lineTask();
    }

    public static void lineTask() {
        List<Line> lines = Arrays.asList(
                new Line(2, -1, 3),
                new Line(1, 1, -5),
                new Line(4, -2, 6),
                new Line(3, 2, -8),
                new Line(-1, 2, 4),
                new Line(2, -1, 1),
                new Line(0, 1, -3),
                new Line(1, 0, -4)
        );

        Line ox = new Line(0, 1, 0);
        Line oy = new Line(1, 0, 0);

        Point x = Line.getIntersection(ox, lines.get(0));
        Point y = Line.getIntersection(oy, lines.get(0));
        System.out.println("Intersection with ox: " +  x);
        System.out.println("Intersection with oy: " +  y);

        Map<String, List<Line>> parallelGroups = groupParallelLines(lines);

        System.out.println("\nGroups of parallel lines:");
        int groupNumber = 1;
        for (List<Line> group : parallelGroups.values()) {
            if (group.size() > 1) {
                System.out.println("Group " + groupNumber++ + ":");
                for (Line line : group) {
                    System.out.println("  " + line);
                }
            }
        }
    }

    public static Map<String, List<Line>> groupParallelLines(List<Line> lines) {
        Map<String, List<Line>> groups = new HashMap<>();

        for (Line line : lines) {
            // Создаем ключ на основе нормализованных коэффициентов
            String key;
            Double slope = line.getSlope();

            if (slope != null) {
                key = String.format("k=%.4f", slope);
            } else {
                key = "vertical_x=" + String.format("%.4f", -line.getC() / line.getA());
            }

            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(line);
        }

        return groups;
    }

    public static void circleTask() {
        List<Circle> circles = Arrays.asList(
                new Circle(0, 0, 5),
                new Circle(2, 2, 3),
                new Circle(4, 4, 7),
                new Circle(1, 1, 4),
                new Circle(3, 3, 2),
                new Circle(5, 0, 6),
                new Circle(0, 5, 3)
        );

        Circle maxAreaCircle = circles.get(0);
        Circle minAreaCircle = circles.get(0);


        for (Circle circle : circles) {
            if (circle.getArea() > maxAreaCircle.getArea()) {
                maxAreaCircle = circle;
            }
            if (circle.getArea() < minAreaCircle.getArea()) {
                minAreaCircle = circle;
            }
        }

        System.out.println("\n\n" + "The largest in area: " + maxAreaCircle);
        System.out.println("\n" + "Smallest in area: " + minAreaCircle);


       groupCirclesByLine(circles);

    }

    public static void groupCirclesByLine(List<Circle> circles) {
        if (circles.size() < 2) {
            System.out.println("\nNot enough circles to group");
            return;
        }

        // Используем HashMap с ключом для группировки прямых
        Map<String, List<Circle>> lineGroups = new HashMap<>();

        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                Circle circle1 = circles.get(i);
                Circle circle2 = circles.get(j);

                Line line = new Line(circle1.getCenter(), circle2.getCenter());
                String lineKey = getLineKey(line);

                if (!lineGroups.containsKey(lineKey)) {
                    List<Circle> group = findCirclesOnLine(circles, line);
                    if (group.size() > 1) {
                        lineGroups.put(lineKey, group);
                    }
                }
            }
        }

        printCollinearGroups(new ArrayList<>(lineGroups.values()));
    }

    private static String getLineKey(Line line) {
        if (Math.abs(line.getB()) < 1e-9) {
            return String.format("vertical_%.6f", -line.getC()/line.getA());
        }

        double k = -line.getA()/line.getB();
        double m = -line.getC()/line.getB();
        return String.format("slope_%.6f_%.6f", k, m);
    }

    private static List<Circle> findCirclesOnLine(List<Circle> circles, Line line) {
        List<Circle> group = new ArrayList<>();

        for (Circle circle : circles) {
            if (line.containsPoint(circle.getCenter())) {
                group.add(circle);
            }
        }

        return group;
    }

    private static void printCollinearGroups(List<List<Circle>> groups) {
        System.out.println("\nGroups of circles with centers on the same line: ");

        if (groups.isEmpty()) {
            System.out.println("No groups found");
            return;
        }

        for (int i = 0; i < groups.size(); i++) {
            List<Circle> group = groups.get(i);

            // Создаем прямую для отображения уравнения
            Line line = new Line(group.get(0).getCenter(), group.get(1).getCenter());

            System.out.printf("\nGroup %d (%d circle):\n", i + 1, group.size());
            System.out.println("Line: " + line);

            for (Circle circle : group) {
                System.out.println("  " + circle);
            }
        }
    }

    public static void quadrilateralTask() {
        List<Quadrilateral> quadrilaterals = Arrays.asList(
                new Quadrilateral(0, 0, 2, 0, 2, 2, 0, 2), // Квадрат
                new Quadrilateral(0, 0, 4, 0, 4, 2, 0, 2), // Прямоугольник
                new Quadrilateral(0, 0, 2, 1, 4, 0, 2, -1), // Ромб
                new Quadrilateral(0, 0, 3, 0, 4, 2, 1, 2), // Параллелограмм
                new Quadrilateral(0, 0, 3, 1, 2, 3, 1, 2), // Произвольный
                new Quadrilateral(1, 1, 3, 1, 3, 3, 1, 3), // Квадрат
                new Quadrilateral(0, 0, 5, 0, 5, 3, 0, 3), // Прямоугольник
                new Quadrilateral(1, 0, 2, 2, 1, 4, 0, 2) // Ромб
        );

        Map<String, List<Quadrilateral>> quadsByType = new HashMap<>();

        for (Quadrilateral quad : quadrilaterals) {
            String type = quad.getType();
            quadsByType.putIfAbsent(type, new ArrayList<>());
            quadsByType.get(type).add(quad);
        }

        // Анализируем каждую группу
        for (Map.Entry<String, List<Quadrilateral>> entry : quadsByType.entrySet()) {
            String type = entry.getKey();
            List<Quadrilateral> typeQuads = entry.getValue();

            System.out.println("\n=== " + type + " ===");
            System.out.println("Quantity: " + typeQuads.size());

            if (!typeQuads.isEmpty()) {
                // Находим четырехугольники с минимальной и максимальной площадью
                Quadrilateral minAreaQuad = typeQuads.get(0);
                Quadrilateral maxAreaQuad = typeQuads.get(0);


                for (Quadrilateral quad : typeQuads) {
                    if (quad.getArea() < minAreaQuad.getArea()) {
                        minAreaQuad = quad;
                    }
                    if (quad.getArea() > maxAreaQuad.getArea()) {
                        maxAreaQuad = quad;
                    }
                }

                System.out.println("Minimum area: " + minAreaQuad + " (S=" +
                        String.format("%.2f", minAreaQuad.getArea()) + ")");
                System.out.println("Maximum area: " + maxAreaQuad + " (S=" +
                        String.format("%.2f", maxAreaQuad.getArea()) + ")");
            }

            // Выводим все четырехугольники этой группы
            System.out.println("\nAll quadrilaterals:");
            for (Quadrilateral quad : typeQuads) {
                System.out.println("  " + quad);
            }
        }
    }

    public static void triangleTask() {
        List<Triangle> triangles = Arrays.asList(
                new Triangle(0, 0, 1, 0, 0.5, Math.sqrt(3)/2), // Равносторонний
                new Triangle(0, 0, 3, 0, 0, 4),                // Прямоугольный
                new Triangle(0, 0, 4, 0, 2, 3),                // Равнобедренный
                new Triangle(0, 0, 5, 0, 1, 2),                // Произвольный
                new Triangle(0, 0, 2, 0, 1, Math.sqrt(3)),         // Равносторонний
                new Triangle(0, 0, 6, 0, 0, 8),                // Прямоугольный
                new Triangle(0, 0, 3, 0, 1.5, 4)               // Произвольный
        );

        Map<String, List<Triangle>> trianglesByType = new HashMap<>();

        for (Triangle triangle : triangles) {
            String type = triangle.getType();
            trianglesByType.putIfAbsent(type, new ArrayList<>());
            trianglesByType.get(type).add(triangle);
        }

        for (Map.Entry<String, List<Triangle>> entry : trianglesByType.entrySet()) {
            String type = entry.getKey();
            List<Triangle> typeTriangles = entry.getValue();

            System.out.println("\n=== " + type + " треугольники ===");
            System.out.println("Количество: " + typeTriangles.size());

            if (!typeTriangles.isEmpty()) {
                // Находим треугольники с минимальной и максимальной площадью
                Triangle minAreaTriangle = typeTriangles.get(0);
                Triangle maxAreaTriangle = typeTriangles.get(0);

                for (Triangle triangle : typeTriangles) {
                    if (triangle.getArea() < minAreaTriangle.getArea()) {
                        minAreaTriangle = triangle;
                    }
                    if (triangle.getArea() > maxAreaTriangle.getArea()) {
                        maxAreaTriangle = triangle;
                    }
                }

                System.out.println("Минимальная площадь: " + minAreaTriangle + " (S=" +
                        String.format("%.2f", minAreaTriangle.getArea()) + ")");
                System.out.println("Максимальная площадь: " + maxAreaTriangle + " (S=" +
                        String.format("%.2f", maxAreaTriangle.getArea()) + ")");
            }
        }
    }

    public static void pointTask() {
        MovingPoint p1 = new MovingPoint(-1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        MovingPoint p2 = new MovingPoint(1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, -1.0, -1.0, 1.0);
        System.out.println("Point 1: " + p1.toString() + "\nPoint 2: " + p2.toString());
        System.out.println("Do points intersect: " + p1.checkTrajectoryIntersection(p2, 1000, 0.1));
        System.out.println("Distance between points: " + p1.distanceTo(p2));
    }

    // Объявить массив/список/множество и n интервалов и определить
    //расстояние между самыми удаленными концами.
    public static void intervalTask() {
        List<Interval> array = new ArrayList<Interval>();

        array.add(new Interval(1.0, 5.0, true, false));
        array.add(new Interval(3.0, 8.0, false, true));
        array.add(new Interval(-2.0, 2.0));
        array.add(new Interval(10.0, 15.0, true, true));

        double minStart = array.get(0).getStart();
        double maxEnd = array.get(0).getEnd();

        // Находим минимальный начальный и максимальный конечный пункты
        for (Interval interval : array) {
            if (interval.getStart() < minStart) {
                minStart = interval.getStart();
            }
            if (interval.getEnd() > maxEnd) {
                maxEnd = interval.getEnd();
            }
        }

        System.out.println("Maximum distance between ends: " + (maxEnd - minStart));

    }

    public static void polynomialTask() {
        System.out.println("Enter number of polynomes: ");
        int k = scanner.nextInt();
        Polynomial[] array = new Polynomial[k];

        final int degree = 5;
        for (int i = 0; i < array.length; i++) {
            double[] coeff = new double[degree];
            for (int j = 0; j < degree; j++) {
                coeff[j] = random.nextInt(1000) / 100.0;
            }
            array[i] = new Polynomial(coeff);
        }

        System.out.println("Initial array: ");
        printArray(array);

        Polynomial sum = array[0];
        for (int i = 1; i < k; i++) {
            sum = sum.add(array[i]);
        }

        System.out.println("Sum of polynomes: " + sum.toString());
    }

    public static void quadraticEquationTask() {
        System.out.println("Enter number of quadratic equations: ");
        int k = scanner.nextInt();
        QuadraticEquation[] array = new QuadraticEquation[k];

        for (int i = 0; i < array.length; i++) {
            array[i] = new QuadraticEquation(random.nextInt(10), random.nextInt(10), random.nextInt(10));
        }

        System.out.println("Initial array: ");
        printArray(array);

        List<Double> allRoots =  new ArrayList<>();

        for (QuadraticEquation e : array) {
            double[] roots = e.findRoots();
            for (double root : roots) {
                allRoots.add(root);
            }
        }

        Double maxRoot = Collections.max(allRoots);
        Double minRoot = Collections.min(allRoots);

        System.out.println("Minimum root: " + minRoot);
        System.out.println("Maximum root: " + maxRoot);
    }

    public static void complexTask() {
        System.out.println("Enter size of complex array: ");
        int k = scanner.nextInt();
        Complex[] array = new Complex[k];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Complex(random.nextInt(100), random.nextInt(100));
        }

        System.out.println("Initial array: ");
        printArray(array);

        Complex result = new Complex(0, 0);
        for (Complex e : array) {
            result = result.add(e);
        }

        System.out.println("Sum of array elements: " + result.toString());
    }

    public static void fractionTask() {
        System.out.println("Enter size of fraction array: ");
        int k = scanner.nextInt();
        Fraction[] array = new Fraction[k];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Fraction(random.nextInt(100), random.nextInt(100));
        }

        System.out.println("Initial array: ");
        printArray(array);

        processArray(array);
        System.out.println("Modified array: ");
        printArray(array);
    }

    public static void printArray(Object[] array) {
        for (Object e : array) {
            System.out.println(e.toString());
        }
    }


    public static void processArray(Fraction[] array) {
        for (int i = 0; i < array.length - 1; i += 2) {
            // Для четных индексов: добавляем следующий элемент
            array[i] = array[i].add(array[i + 1]);
        }
    }
}
