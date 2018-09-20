package arrays;

import java.util.Arrays;

public class NextGreterNumber {

    public static void main(String[] args) {
        int[] number = {6,9,3,8,6,5,2};
//        int[] number = {6,9,5,8,6,3,2};
        number = getNextBgreatNumber(number);
        System.out.println("Next great number is : " + Arrays.toString(number));
    }

    private static int[] getNextBgreatNumber(int[] number) {
        int length = number.length;
        if (length==1) {
            number[0]= number[0] + 1;
        } else {
            int start= length-2;

            for (int i=length-1;i>=0;i--) {
                if (number[i]>number[start]) {
                    break;
                } else {
                    start--;
                }
            }
            System.out.println(number[start]);
            for (int j=length-1;j>start;j--) {
                if (number[j]>number[start]) {
                    number = swap(number, start, j);
                    number = sortArray(number, start+1);
                    break;
                }
            }
        }
        return number;
    }

//    private static int[] getNextBgreatNumber(int[] number) {
//        int length = number.length;
//        if (length==1) {
//            number[0]= number[0] + 1;
//        } else {
//            int start= length-2;
//            int end = length-1;
//
//            for (int i=length-1;i>=0;i--) {
//                if (number[i]>number[start]) {
//                    number=swap(number, i , start);
//                    number = sortArray(number, start+1);
//                    break;
//                } else {
//                    start--;
//                }
//            }
//        }
//        return number;
//    }

    static int[] sortArray(int[] arr, int start) {

        for (int i = start; i<arr.length; i++) {
            for (int j=i+1;j<arr.length;j++) {
                if (arr[i]>arr[j])
                    swap(arr, i, j);
            }
        }
        return arr;

    }

    private static int[] sortSubarray(int[] number, int i)
    {
        int j = number.length-1;
        // for this sub-array, all the digits would be in there reverse sorted position
        while (i < j)
        {
            swap(number, i, j);
            i += 1;
            j -= 1;
        }
        return number;
    }

    static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }


    private static void getDigitLength(int number) {
//        int len = (int) (Math.log10(number) +1);
//        System.out.println(len);

        int temp = 1;
        int len = 0;
        while (temp<=number) {
            temp = temp*10;
            len++;
        }
        System.out.println(len);
    }
}
