package arrays;

import java.util.Arrays;

public class Unique {

    // To find minimum sum of unique elements.
    static int minSum(int arr[], int n)
    {
        int sum = arr[0], prev = arr[0];

        for (int i = 1; i < n; i++) {

            // If violation happens, make current
            // value as 1 plus previous value and
            // add to sum.
            if (arr[i] <= prev) {
                prev = prev + 1;
                sum = sum + prev;
                arr[i]=prev;

            }

            // No violation.

            else {
                sum = sum + arr[i];
                prev = arr[i];
            }
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        return sum;
    }

    // Drivers code
    public static void main (String[] args) {

//        int arr[] = { 2, 2, 3, 5, 6 };
        int arr[] = { 3, 2, 1, 2, 7 };
        int n = arr.length;

        Arrays.sort(arr);

        System.out.println(minSum(arr, n));
    }
}