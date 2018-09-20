package arrays;

public class PivotElement {

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5};
//        int[] arr = {6,7,1,2,3,4,5};
//        int[] arr = {3,4,5,6,7,1,2};
//        int[] arr = {5,6,7,1,2,3,4};
//        int[] arr = {1,2,3,4,5,6,7};
        int[] arr = {2,3,4,5,6,7,1};
        int i = findPivot(arr);
        System.out.println(arr[i]);

        int[] arr1 = {3,4,5,6,7,1,2};
        i= findPivot(arr1);
        System.out.println(arr1[i]);


    }


    private static int findPivot(int[] arr) {
        if (arr.length<1)
            return -1;
        if ((arr.length==1) || (arr[0]<arr[arr.length-1]))
            return 0;

        int start = 0;
        int end = arr.length-1;
        int mid;

        while (start<=end) {
            mid = (end+start)/2;
            if (arr[mid-1]>arr[mid] || mid==start || mid==end)
                return mid;
            else if (arr[start]<arr[mid]) {
                start = mid+1;
            } else {
                start = mid-1;
            }
        }

        return -1;
    }
}
