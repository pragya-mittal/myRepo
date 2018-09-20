package arrays.matrix;

public class MatrixSubSquare {
    public static void main(String[] args) {
        int matrix[][] = {  { 1, 1, 0, 0, 1, 1 },
                            { 0, 0, 1, 0, 1, 1 },
                            { 1, 1, 1, 1, 1, 0 },
                            { 1, 1, 1, 1, 1, 1 },
                            { 1, 1, 1, 1, 1, 1 },
                            { 0, 1, 1, 1, 1, 1 },
                            { 1, 0, 0, 0, 1, 1 }
        };
        System.out.println(maximumSizeSquareSubmatrixWithAllOnes(matrix));
    }

    private static int maximumSizeSquareSubmatrixWithAllOnes(int[][] matrix) {
        int rows=matrix.length;
        int columns = matrix[0].length;

        int squareMatric[][] = new int[rows][columns];

        int max=0;
        for (int i=0;i<rows;i++) {
            squareMatric[i][0] = matrix[i][0];
            if (max<squareMatric[i][0])
                max=squareMatric[i][0];
        }

        for (int j=0;j<columns;j++) {
            squareMatric[0][j] = matrix[0][j];
            if (max<squareMatric[0][j])
                max=squareMatric[0][j];

        }

        for (int i=1;i<rows;i++) {
            for (int j=1;j<columns;j++) {
                if (matrix[i][j]==1) {
                    squareMatric[i][j] = 1 + Math.min(squareMatric[i-1][j], Math.min(squareMatric[i-1][j-1], squareMatric[i][j-1]));
                } else {
                    squareMatric[i][j]=0;
                }
                if (max<squareMatric[i][j])
                    max=squareMatric[i][j];
            }
        }

        for (int i=0;i<rows;i++) {
            for (int j=0;j<columns;j++) {
                System.out.print(squareMatric[i][j] + " ");
            }
            System.out.println();
        }
        return max;
    }
}
