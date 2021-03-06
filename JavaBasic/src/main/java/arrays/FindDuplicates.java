package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicates {
    public static Set<Integer> getDuplicates(int[] array) {
        int n = array.length;
        Set<Integer> duplicates = new HashSet<Integer>();
        for(int i = 0; i < n; i++) {
            // Make the array element at index array[i] negative if it is positive
            if(array[Math.abs(array[i])] > 0) {
                array[Math.abs(array[i])] = -array[Math.abs(array[i])];
            } else {
                // If the element at index array[i] is negative, it means we have seen it before, so it is a duplicate
                duplicates.add(Math.abs(array[i]));
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 2, 6, 1, 6, 3, 5};
        Set<Integer> duplicates = getDuplicates(array);
        System.out.println(Arrays.toString(duplicates.toArray()));
    }
}
