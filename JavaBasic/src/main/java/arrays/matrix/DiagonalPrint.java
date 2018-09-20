package arrays.matrix;

import java.util.Arrays;

public class DiagonalPrint {
    public static void printMatrixDiagonally(int[][] matrix) {

        int row, col;
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        for (int k = 0; k < rowCount; k++) {
            for (row = k, col = 0; row >= 0 && col < columnCount; row--, col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        for (int k = 1; k < columnCount; k++) {
            for (row = rowCount - 1, col = k; row >= 0 && col < columnCount; row--, col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    static void printDiagonalDownward(int[][] matrix) {
        int row, col;
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        for (int i=0;i<columnCount;i++) {
           for (row=0,col=i;row<rowCount && col>=0;row++,col--)
               System.out.print(matrix[row][col] + " ");
            System.out.println();
        }

        for (int i=1;i<rowCount;i++) {
            for (row=i,col=columnCount-1; row<rowCount&& col>=0; row++, col--)
                System.out.print(matrix[row][col] + " ");
            System.out.println();

        }

    }

    public static void main(String[] args) {
        int row = 4, col = 5;
        int k = 1;
        int matrix[][] = { {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20}, };

        System.out.println("Input Matrix");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println("\nPrinting Matrix Diagonally");
        printMatrixDiagonally(matrix);

        System.out.println("\nPrinting Matrix Diagonally Down");
        printDiagonalDownward(matrix);
    }
}
