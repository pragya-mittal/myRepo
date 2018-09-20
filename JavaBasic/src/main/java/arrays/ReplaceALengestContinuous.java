package arrays;


public class ReplaceALengestContinuous {
    public int getRequiredIndex(int[] binaryArray)
    {
        int prevZeroIndex = -1, prevPrevZeroIndex = -1;
        int maxLength = -1, requiredIndex = -1, currLength = -1;

        for (int i = 0; i < binaryArray.length; i++)
        {
            if (binaryArray[i] == 0)
            {
                if (prevPrevZeroIndex != -1)
                {
                    currLength = i - prevPrevZeroIndex - 1;
                    if (currLength > maxLength)
                    {
                        maxLength = currLength;
                        requiredIndex = prevZeroIndex;
                    }
                }
                prevPrevZeroIndex = prevZeroIndex;
                prevZeroIndex = i;
            }
        }

        // if number of 0s is less than 3
        if (maxLength == -1)
        {
            if (prevPrevZeroIndex != -1) // if there are two 0s
            {
                // the length of 1s sequence after replacing 0 at 'prevPrevZeroIndex' would be 'prevZeroIndex'
                // and the length of 1s sequence after replacing 0 at 'prevZeroIndex' would be (binaryArray.length - prevPrevZeroIndex - 1)
                if (prevZeroIndex > (binaryArray.length - prevPrevZeroIndex - 1))
                {
                    requiredIndex = prevPrevZeroIndex;
                }
                else
                {
                    requiredIndex = prevZeroIndex;
                }
            }
            else // if there is one or no 0s present
            {
                requiredIndex = prevZeroIndex;
            }
        }
        return requiredIndex;
    }

    public static void main(String[] args)
    {
        ReplaceALengestContinuous solution = new ReplaceALengestContinuous();

//        int [] binaryArray = {0,0,1,1,0,0,1,1,0,1};
        int [] binaryArray = {0,1,1,0,1,1,1};

        System.out.println(solution.getRequiredIndex(binaryArray));
    }
}

//public class ReplaceALengestContinuous {
//    public static void main(String[] args) {
//        int[] arr = {0,0,1,1,1,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,1,1,0};
//        getzeroindex(arr);
//    }
//
//    private static void getzeroindex(int[] arr) {
//        int count = 0;
//        int max = 0;
//        int index = -1;
//
//        for (int i=0;i<arr.length; i++) {
//            if (arr[i]==1) {
//                count++;
//                if (max<count)
//                    max=count;
//            }
//            else if (arr[i]==0 && arr[i+1]==1 && arr[i-1]==1) {
//                if (index<0) {
//                    count=1;
//                    index=i;
//                } else {
//                    count--;
//                    index=i;
//                }
//            }
//        }
//    }
//}
