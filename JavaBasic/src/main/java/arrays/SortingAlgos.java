package arrays;

import java.util.Arrays;

public class SortingAlgos {

    private static void bubbleSort(int[] array) {
        for (int i=0;i<array.length;i++) {
            for (int j=i+1;j<array.length;j++) {
                if (array[i]>array[j]) {
                    swap(array, i, j);
                }
            }
        }
    }


//    private static void selectionSort(int[] array) {
//        for (int i=0;i<array.length;i++) {
//            for (int j=0;j<(array.length-i-1);j++) {
//                if (array[j]>array[j+1]) {
//                    swap(array, j, j+1);
//                }
//            }
//        }
//    }

    private static void selectionSort(int[] array) {
        for (int i=0;i<array.length-1;i++) {
            int min = i;
            for (int j=i+1;j<array.length;j++) {
                if (array[min]>array[j]) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }


    private static void insertionSort(int[] array) {
        for (int i=0;i<array.length;i++) {
            for (int j=i;j>0;j--) {
                if (array[j]<array[j-1]) {
                    swap(array,j,j-1);
                }
            }
        }
    }





    private static void swap(int[] array, int maxindex, int minindex) {
        int temp = array[maxindex];
        array[maxindex] = array[minindex];
        array[minindex] = temp;
    }


    public static void main(String[] args) {
        int[] array = {12, 35, 87, 26, 9, 28, 7};
        bubbleSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] array1 = {12, 35, 87, 26, 9, 28, 7};
        selectionSort(array1);
        System.out.println("Sorted array: " + Arrays.toString(array1));

        int[] array2 = {12, 35, 87, 26, 9, 28, 7};
        insertionSort(array2);
        System.out.println("Sorted array: " + Arrays.toString(array2));
    }

}

