package arrays.matrix;

public class Try {
    /*
    find if matrix has same elements diagonal wise
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int k = 1; k < row; k++) {
            for (int i = k, j = 1; i < row && j < col; i++, j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
            }
        }
        for (int k = 1; k < col; k++) {
            for (int j = k, i = 1; j < col && i < row; i++, j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Try ques = new Try();
        int[][] a = {
                {1, 2, 3},
                {5, 1, 2},
                {9, 5, 1},
                {1, 9, 5}
        };
        System.out.println(ques.isToeplitzMatrix(a));
    }
}
