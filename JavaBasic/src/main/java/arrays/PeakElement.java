package arrays;

public class PeakElement {
    public static Integer getOnePeakElement(int[] array) {

        if (array == null || array.length == 0) {
            return null;
        }

        int n = array.length;

        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == n - 1 || array[mid] >= array[mid + 1])) {
                return array[mid]; // array[mid] is peak element
            } else if (mid > 0 && array[mid - 1] > array[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return null;
    }

    static void getAllPeakElement(int[] array) {
        getAllPeakElement(array, 0, array.length-1);
    }

    public static void getAllPeakElement(int[] array, int start, int end) {

        if (array == null || array.length == 0 || start>end) {
            return;
        }

        int mid = (start + end) / 2;

//        if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == end || array[mid] >= array[mid + 1])) {
        if ((array[mid - 1] <= array[mid]) && (array[mid] >= array[mid + 1])) {
            System.out.println("Peak : " + array[mid]); // array[mid] is peak element
        }
        getAllPeakElement(array, start, mid-1);
        getAllPeakElement(array, mid+1, end);
    }

    public static void main(String[] args) {
//        int[] array = { 15, 20, 25, 35, 45, 50, 60 };
//        int[] array = { 1,4,3,6,7,5 };
        int[] array = { 1, 5, 22, 7, 8, 15, 20, 25, 30  };
        Integer peak = getOnePeakElement(array);
        System.out.println(peak != null ? "Peak Element is " + peak : "No peak element!");

        getAllPeakElement(array);
    }
}
