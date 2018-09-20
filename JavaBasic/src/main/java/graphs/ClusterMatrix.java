package graphs;

public class ClusterMatrix {
    private static int size;
    public static void main(String[] args)
    {
        int[][] matrix = {
                {1, 0, 1, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 1, 1},
                {0, 1, 0, 1, 1},
        };


        System.out.println("No " + findNumberofClusters(matrix));
    }

    private static int findNumberofClusters(int[][] matrix) {
        int rows = matrix.length;
        int columns=matrix[0].length;
        boolean temp[][] = new boolean[rows][columns];

        int[] vertex = {-1, 0, 1};

        int count=0;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<columns;j++) {
                if (matrix[i][j]==1) {
                  if (temp[i][j]==false) {
                      size =0;
                      traverseDFS(matrix, temp, i, j);
                      System.out.println(size);
                      count++;
                  }
                }
            }
        }

        return count;
    }

    private static void traverseDFS(int[][] matrix, boolean[][] visited, int rowIndex, int colIndex) {
        if (visited[rowIndex][colIndex])
            return;

        size++;
        visited[rowIndex][colIndex] = true;
        int[] vertex = {-1,0,1};
        for (int i=-1; i<2; i++) {
            for (int j=-1; j<2; j++) {
                if (i==0 && j==0)
                    continue;

                if (neighbourExists(matrix, rowIndex + i, colIndex + j)) {
                    traverseDFS(matrix, visited,rowIndex + i, colIndex + j);
                }

            }
        }
    }

    private static boolean neighbourExists(int[][] matrix, int i, int j) {
        if ((i >= 0) && (i < matrix.length) && (j >= 0) && (j < matrix[0].length)) {
            if (matrix[i][j] == 1) {
                return true;
            }
        }
        return false;
    }

}

// Also do
// http://www.techiedelight.com/find-shortest-path-in-maze/
// https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/


