package arrays.matrix;

public class SearchMatrix {
    public static boolean stairSearch(int matrix[][], int n, int element) {
        if (element < matrix[0][0] || element > matrix[n-1][n-1])
            return false;
        int r = 0; // row
        int c = n-1;// column
        while (r <= n-1 && c >= 0) {
            if (matrix[r][c] < element)
                r++;
            else if (matrix[r][c] > element)
                c--;
            else
                return true;
        }
        return false;
    }


    public static void main(String args[])
    {
        int[][] mat =  {     { 2, 6, 7, 11},
                             { 3, 8, 10, 12},
                             { 4, 9, 11, 13},
                             { 5, 15, 16, 18}
        };

        System.out.println(stairSearch(mat, 4, 9));

    }
}
