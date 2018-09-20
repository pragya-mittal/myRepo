package arrays.strings;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        int[]array = {1, 3, 9, 2};
        int num = 5;
        possibleSubsets(array);

        printSubsets(array);
        printSubsets(array, 3);
        printSubsetsModulom(array, 3);

//        System.out.println(subsetSum(array,0, array.length, num));
    }

    // doesnt work properly
    public static void possibleSubsets(int[] array) {
        if (array.length==0)
            return;

        possibleSets(array, 0, array.length-1);
    }

    static void possibleSets(int[] array, int start, int end) {
        if (start>end) {
            return;
        }

        System.out.println();
        for (int i=start;i<=end;i++)
            System.out.print(array[i] + ",");

        possibleSets(array, start, end-1);
        possibleSets(array, start+1, end);

    }

    static void printSubsets(int set[])
    {
        int n = set.length;

//        System.out.println(1<<5);
        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1<<n); i++)
        {
            System.out.print("{ ");

            // Print current subset
            for (int j = 0; j < n; j++)

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0)
                    System.out.print(set[j] + " ");

            System.out.println("}");
        }
    }

    static void printSubsets(int set[], int m)
    {
        int n = set.length;
        List<Integer> list;

//        System.out.println(1<<5);
        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1<<n); i++)
        {
            int sum =0;
            list = new ArrayList();
            // Print current subset
            for (int j = 0; j < n; j++) {

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0) {
                    sum = sum + set[j];
                    list.add(set[j]);
//                    System.out.print(set[j] + " ");
                }
            }

            if (sum==m)
                System.out.println(list);

        }
    }

    static void printSubsetsModulom(int set[], int m)
    {
        int n = set.length;
        List<Integer> list;

//        System.out.println(1<<5);
        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1<<n); i++)
        {
            int sum =0;
            list = new ArrayList();
            // Print current subset
            for (int j = 0; j < n; j++) {

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0) {
                    sum = sum + set[j];
                    list.add(set[j]);
//                    System.out.print(set[j] + " ");
                }
            }

            if (sum%m==0)
                System.out.println(list);

        }
    }
}
