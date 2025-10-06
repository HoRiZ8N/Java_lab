package Lab_2;

import java.util.Random;
import java.util.Scanner;


public class Main {
    static float[][] sourceMatrix;
    static float[][] matrix;

    public static void main(String[] args) {
        int len = inputMatrixSize();
        sourceMatrix = new float[len][len];
        matrix = new float[len][len];
        fillRandom(sourceMatrix);
        System.out.println("Исходная матрица: ");
        printMatrix(sourceMatrix);
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
    }

//  Ввести с консоли len-размерность матрицы a[len][len].
    public static int inputMatrixSize() {
        Scanner scaner = new Scanner(System.in);
        System.out.print("Введите размерность матрицы: ");
        return scaner.nextInt();
    }

//  Задать значения элементов матрицы в интервале значений от -len до len с помощью генератора случайных чисел.
    public static void fillRandom(float[][] matrix) {
        int len = matrix.length;
        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = Math.round((rnd.nextFloat() * (len * 2) - len) * 10.0f) / 10.0f;
            }
        }
    }
//  Упорядочить строки матрицы в порядке возрастания значений элементов k-го столбца
    public static void task1() {
        copyMatrix(sourceMatrix, matrix);
        System.out.println("Введите номер столбца по которому будет производиться сортировка: ");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        // bubbleSort
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix.length - i - 1; j++) {
                // Сравниваем элементы в k-м столбце
                if (matrix[j][k] > matrix[j + 1][k]) {
                    swapRows(matrix, j, j + 1);
                }
            }
        }
        System.out.println("Матрица после упорядочивания в порядке возрастания значений элементов k-го столбца");
        printMatrix(matrix);
    }

//  Выполнить циклический сдвиг заданной матрицы на k позиций вправо.
    public static void task2() {
        copyMatrix(sourceMatrix, matrix);
        System.out.println("Введите на сколько позиций выполнить сдвиг вправо: ");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        for (float[] row : matrix) {
            shiftRowRight(row, k);
        }

        System.out.printf("Матрица после сдвига на %d позиций:\n", k);
        printMatrix(matrix);
    }
//  Найти и вывести наибольшее число возрастающих элементов матрицы, идущих подряд
    public static void task3() {
        copyMatrix(sourceMatrix, matrix);
        int len = matrix.length;
        float next, curr;
        int seqLen = 1, maxSeqLen = 1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == len - 1 && j == len - 1) {
                    break;
                }
                curr = matrix[i][j];

                if (j == len - 1) {
                    next = matrix[i + 1][0];
                } else {
                    next = matrix[i][j + 1];
                }

                if (curr <= next) {
                    seqLen++;
                    maxSeqLen = Math.max(maxSeqLen, seqLen);
                } else {
                    seqLen = 1;
                }
            }
        }
        System.out.printf("Максимальная длина возрастающей последовательности: %d\n", maxSeqLen);
    }

    // Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
    public static void task4() {
        copyMatrix(sourceMatrix, matrix);
        int len = matrix.length;
        float totalSum = 0;

        for (float[] row : matrix) {
            int first = -1, second = -1;

            for (int j = 0; j < len; j++) {
                if (row[j] > 0) {
                    if (first == -1) {
                        first = j;
                    } else {
                        second = j;
                        break;
                    }
                }
            }

            if (first != -1 && second != -1) {
                for (int j = first + 1; j < second; j++) {
                    totalSum += row[j];
                }
            }
        }

        System.out.println("Общая сумма по всем строкам: " + totalSum);
    }

    // Вывести числа от 1 до k в виде матрицы len x len слева направо и сверху вниз
    public static void task5() {
        System.out.println("Введите число k, для вывода в виде квадратной матрицы: ");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int len = (int) Math.sqrt(k);

        System.out.println("Матрица " + len + "x" + len + ":");
        int count = 1;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (count <= k) {
                    System.out.printf("%3d ", count);
                    count++;
                }
            }
            System.out.println();
        }
    }

    // Округлить все элементы матрицы до целого числа.
    public static void task6() {
        copyMatrix(sourceMatrix, matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.round(matrix[i][j]);
            }
        }

        System.out.println("Матрица после округления всех элементов до целого числа: ");
        printMatrix(matrix);
    }

    // Повернуть матрицу на 90 градусов против часовой стрелки.
    public static void task7() {
        copyMatrix(sourceMatrix, matrix);
        int len = matrix.length;
        float[][] rotated = new float[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                rotated[len - j - 1][i] = matrix[i][j];
            }
        }

        copyMatrix(rotated, matrix);

        System.out.println("Матрица после поворота на 90 против часовой стрелки: ");
        printMatrix(matrix);
    }

    // Вычислить определитель матрицы.
    public static void task8() {
        copyMatrix(sourceMatrix, matrix);
        System.out.printf("Определитель матрицы: %f\n", determinant(matrix));
    }

    // Построить матрицу, вычитая из элементов каждой строки матрицы ее среднее арифметическое.
    public static void task9() {
        copyMatrix(sourceMatrix, matrix);
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            float mean = getArithmeticMeanRow(matrix, i);
            for (int j = 0; j < len; j++) {
                matrix[i][j] -= mean;
            }
        }

        System.out.println("Матрица после вычитания из элементов каждой строки ее среднее арифметическое: ");
        printMatrix(matrix);
    }

    // Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
    public static void task10() {
        copyMatrix(sourceMatrix, matrix);
        matrix = removeMaxElements(matrix);
        System.out.println("Матрица после удаления всех строк и столбцов, которые содержат максимальный элемент: ");
        printMatrix(matrix);
    }

    // Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
    public static void task11() {
        float[][] mtx = {
                {0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 1.2f, 0.0f, 2.3f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 3.4f, 0.0f, 4.5f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f, 0.0f}
        };
        System.out.println("Исходная матрица для примера уплотнения: ");
        printMatrix(mtx);
        float[][] compressed = compressMatrix(mtx);
        System.out.println("Матрица после уплотнения: ");
        printMatrix(compressed);
    }

    public static float[][] removeMaxElements(float[][] matrix) {
        if (matrix.length == 0) return matrix;

        // Находим максимальный элемент
        float max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        System.out.println("Максимальный элемент: " + max);

        // Отмечаем строки и столбцы для удаления
        boolean[] removeRows = new boolean[matrix.length];
        boolean[] removeCols = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (Math.abs(matrix[i][j] - max) < 1e-6) {
                    removeRows[i] = true;
                    removeCols[j] = true;
                }
            }
        }

        // Подсчитываем сколько строк и столбцов останется
        int rowsLeft = 0;
        for (boolean remove : removeRows) {
            if (!remove) rowsLeft++;
        }

        int colsLeft = 0;
        for (boolean remove : removeCols) {
            if (!remove) colsLeft++;
        }

        if (rowsLeft == 0 || colsLeft == 0) {
            return new float[0][0];
        }

        // Создаем новую матрицу
        float[][] result = new float[rowsLeft][colsLeft];
        int newI = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (removeRows[i]) continue;

            int newJ = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (removeCols[j]) continue;

                result[newI][newJ] = matrix[i][j];
                newJ++;
            }
            newI++;
        }

        return result;
    }

    private static float[][] compressMatrix(float[][] matrix) {
        if (matrix.length == 0) return matrix;

        boolean[] zeroRows = findZeroRows(matrix);
        boolean[] zeroCols = findZeroColumns(matrix);

        int newRows = countNonZero(zeroRows);
        int newCols = countNonZero(zeroCols);

        if (newRows == 0 || newCols == 0) {
            System.out.println("После уплотнения матрица пуста!");
            return new float[0][0];
        }

        float[][] compressed = new float[newRows][newCols];

        int newI = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (zeroRows[i]) continue;

            int newJ = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (zeroCols[j]) continue;

                compressed[newI][newJ] = matrix[i][j];
                newJ++;
            }
            newI++;
        }

        return compressed;
    }

    private static boolean[] findZeroRows(float[][] matrix) {
        boolean[] zeroRows = new boolean[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            boolean isZeroRow = true;
            for (int j = 0; j < matrix[i].length; j++) {
                if (Math.abs(matrix[i][j]) > 1e-10) {
                    isZeroRow = false;
                    break;
                }
            }
            zeroRows[i] = isZeroRow;
        }

        return zeroRows;
    }

    private static boolean[] findZeroColumns(float[][] matrix) {
        if (matrix.length == 0) return new boolean[0];

        boolean[] zeroCols = new boolean[matrix[0].length];

        for (int j = 0; j < matrix[0].length; j++) {
            boolean isZeroCol = true;
            for (int i = 0; i < matrix.length; i++) {
                if (Math.abs(matrix[i][j]) > 1e-10) {
                    isZeroCol = false;
                    break;
                }
            }
            zeroCols[j] = isZeroCol;
        }

        return zeroCols;
    }

    private static int countNonZero(boolean[] array) {
        int count = 0;
        for (boolean value : array) {
            if (!value) count++;
        }
        return count;
    }

    private static float getArithmeticMeanRow(float[][] matrix, int i) {
        float res = 0;
        int len = matrix.length;

        for (int j = 0; j < len; j++) {
            res += matrix[i][j];
        }

        res /= len;
        return res;
    }

    public static void swapRows(float[][] matrix, int i, int j) {
        float[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }

    public static void printMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            for (float e : row) {
                System.out.printf("%.2f ", e);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void shiftRowRight(float[] row, int k) {
        int len = row.length;
        if (len == 0 || k == 0) {
            return;
        }

        k %= len;

        if (k < 0) {
            k += len;
        }
        if (k == 0) {
            return;
        }

        float[] temp = new float[len];
        System.arraycopy(row, 0, temp, 0, len);

        for (int i = 0; i < len; i++) {
            int newIndex = (i + k) % len;
            row[newIndex] = temp[i];
        }
    }

    private static void copyMatrix(float[][] source, float[][] target) {
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, target[i], 0, source[i].length);
        }
    }

    private static float determinant(float[][] matrix) {
        int len = matrix.length;

        if (len == 1) {
            return matrix[0][0];
        }

        if (len == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        if (len == 3) {
            return matrix[0][0] * matrix[1][1] * matrix[2][2] +
                    matrix[0][1] * matrix[1][2] * matrix[2][0] +
                    matrix[0][2] * matrix[1][0] * matrix[2][1] -
                    matrix[0][2] * matrix[1][1] * matrix[2][0] -
                    matrix[0][1] * matrix[1][0] * matrix[2][2] -
                    matrix[0][0] * matrix[1][2] * matrix[2][1];
        }

        float det = 0;
        for (int j = 0; j < len; j++) {
            det += matrix[0][j] * cofactor(matrix, 0, j);
        }
        return det;
    }

    private static float cofactor(float[][] matrix, int row, int col) {
        return (float) (Math.pow(-1, row + col) * determinant(minor(matrix, row, col)));
    }

    private static float[][] minor(float[][] matrix, int row, int col) {
        int len = matrix.length;
        float[][] minor = new float[len - 1][len - 1];

        int minorRow = 0;
        for (int i = 0; i < len; i++) {
            if (i == row) continue;

            int minorCol = 0;
            for (int j = 0; j < len; j++) {
                if (j == col) continue;

                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }
}
