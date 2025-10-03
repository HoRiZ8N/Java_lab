package Lab_2;

import java.util.Random;
import java.util.Scanner;


public class Main {
    static int matrixSize;
    static int[][] matrix;

    public static void main(String[] args) {
        inputMatrixSize();
        matrix = new int[matrixSize][matrixSize];
        fillRandom();
        System.out.println("Исходная матрица: ");
        printMatrix();
        task1();
        task2();
        task3();
    }

//  Ввести с консоли n-размерность матрицы a[n][n].
    public static void inputMatrixSize() {
        Scanner scaner = new Scanner(System.in);
        System.out.print("Введите размерность матрицы n: ");
        matrixSize = scaner.nextInt();
    }

//  Задать значения элементов матрицы в интервале значений от -n до n с помощью генератора случайных чисел.
    public static void fillRandom() {
        Random rnd = new Random();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = rnd.nextInt(matrixSize * 2) - matrixSize;
            }
        }
    }
//  Упорядочить строки матрицы в порядке возрастания значений элементов k-го столбца
    public static void task1() {
        System.out.println("Введите k: ");
        Scanner scaner = new Scanner(System.in);
        int k = scaner.nextInt();
        // bubbleSort
        for (int i = 0; i < matrixSize - 1; i++) {
            for (int j = 0; j < matrixSize - i - 1; j++) {
                // Сравниваем элементы в k-м столбце
                if (matrix[j][k] > matrix[j + 1][k]) {
                    swapRows(j, j + 1);
                }
            }
        }
        System.out.println("Матрица после упорядочивания в порядке возрастания значений элементов k-го столбца");
        printMatrix();
    }

//  Выполнить циклический сдвиг заданной матрицы на k позиций вправо (влево, вверх, вниз).
    public static void task2() {

    }
//  Найти и вывести наибольшее число возрастающих\убывающих элементовматрицы, идущих подряд
    public static void task3() {

    }
    public static void swapRows(int i, int j) {
        int[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }

    public static void printMatrix() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
