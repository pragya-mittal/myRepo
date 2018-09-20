package trees;

public class CheckIdenticalBST {

    boolean checkIdentical(int[] arr1, int[] arr2) {
        if (!(arr1.length==arr2.length))
            return false;
        if (arr1[0]!=arr2[0])
            return false;
        int root, min, max;
        int left, right;
        min = -1; max = 10;
        for (int i=0; i < arr1.length; i++) {
            root = arr1[i];
            left = getVal(arr1, min, root, root);
            right = getVal(arr1, root, max, root);
            System.out.println("Root : " + root + " , left : " + left + " , right : " + right);
//            min = left;max = right;
        }
        return true;
    }

    int getVal(int[] arr, int min, int max, int root) {
        if (arr.length==0)
            return -1;
        int val=-1,j=0;
        while (arr[j]!=root)
            j++;
        for (int i = j; i < arr.length; i++) {
            if (arr[i] < max && arr[i] > min) {
                val = arr[i];
                break;
            }
        }
        return val;
    }


    public static void main(String[] args) {
        int arr1[] = {3,5,4,6,1,0,2};
        int arr2[] = {3,1,5,2,4,6,0};
        CheckIdenticalBST checkIdenticalBST = new CheckIdenticalBST();
        checkIdenticalBST.checkIdentical(arr1, arr2);


    }
}
