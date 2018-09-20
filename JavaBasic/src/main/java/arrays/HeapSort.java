package arrays;

public class HeapSort {

    // Driver program
    public static void main(String args[])
    {
        int arr[] = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        for (int i=0;i<n;i++)
            System.out.print(arr[i] + " , ");

    }

    private void sort(int[] arr) {
        int n =arr.length;

        for (int i=(n-1)/2;i>=0;i--) {
            heapify(arr, i, n);
        }

        for (int i=n-1;i>=0;i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    private void heapify(int[] arr, int i, int n) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left<n && arr[largest]<arr[left])
            largest=left;

        if (right<n && arr[largest]<arr[right])
            largest=right;

        if (largest!=i) {
            swap (arr, i , largest);
            heapify(arr, largest, n);
        }
    }

    private void swap(int[] arr, int i, int largest) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
    }
}
