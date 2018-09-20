package arrays.matrix;

public class CommonElement {
    public static void main(String[] args)
    {
        int[][] arrays = {
                {23, 34, 67, 89, 123, 566, 1000},
                {11, 22, 23, 24,33, 37, 185, 566, 987, 1223, 1234},
                {23, 43, 67, 98, 566, 678},
                {1, 4, 5, 23, 34, 76, 87, 132, 566, 665},
                {1, 2, 3, 23, 24, 344, 566}
        };

        printCommonElements(arrays);
        printCommon(arrays);
    }

    private static void printCommon(int[][] arrays) {
        int rows = arrays.length;
        int columns = arrays[0].length;

        boolean arr[] = new boolean[rows];
        arr[0]=true;

        int arrIndex[] = new int[rows];
        for (int i=0;i<rows;i++)
            arrIndex[i]=0;

        for (int i=0; i< columns; i++) {
            for (int m=1; m < rows; m++) {
                for (int j=arrIndex[m]; j< arrays[m].length; j++) {
                    if (arrays[0][i] == arrays[m][j]) {
                        arr[m] = true;
                        arrIndex[m] = j;
                        break;
                    } else {
                        arr[m] = false;
                    }
                }
            }

            int k=0;
            boolean flag = true;
            while (k<rows) {
                flag = flag & arr[k];
                k++;
            }
            if (flag)
                System.out.print( " Nu " + arrays[0][i] + " ");
        }
    }

    private static void printCommonElements(int[][] arrays) {
        int rows = arrays.length;
        int columns = arrays[0].length;

        boolean arr[] = new boolean[rows];
        arr[0]=true;

        int arrIndex[] = new int[rows];

//        for(int i=0;i<rows;i++) {
//            for (int j=0;j<columns;j++)
//                indexMatrix[i][j] = 0;
//        }

        for (int i=0; i< columns; i++) {
//            System.out.print(" Yo " + arrays[0][i]);
            for (int m=1; m < rows; m++) {
                for (int j=0; j< arrays[m].length; j++) {
                    if (arrays[0][i] == arrays[m][j]) {
                        arr[m] = true;
                        break;
                    } else {
                        arr[m] = false;
                    }
                }
            }

            int k=0;
            boolean flag = true;
            while (k<rows) {
                flag = flag & arr[k];
                k++;
            }
            if (flag)
                System.out.print( " Nu " + arrays[0][i] + " ");
        }
    }
}
