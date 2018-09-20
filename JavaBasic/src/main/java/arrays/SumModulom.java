package arrays;

public class SumModulom {
    public static void main(String[] args) {
        int[] input = { 3, 3, 9, 9, 5 };
        int modulo = 7;
        System.out.println("Maximum subarray sum is " + maxModuloSum(input, modulo));
    }

    private static int maxModuloSum(int[] input, int m) {
//        int[] tempArr = new int[input.length];

//        tempArr[0] = input[0]%m;


        for (int i=0; i < input.length; i++) {
            if (i==0)
                input[i] = input[i]%m;
            else
                input[i] = (input[i-1] + input[i])%m;
        }

        int max =input[0];
        int sum = 0;
        for (int i=0; i < input.length; i++) {
            for (int j=0;j<i;j++) {
                sum = Math.max(input[i], (input[i]-input[j] + m)%m);
            }
            if (max<sum)
                max=sum;
        }
        return max;
    }

}
