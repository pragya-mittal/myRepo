package trees;

public class CheckIdenticalBSTs
{
    private boolean checkIfSameBSTs(int[] arrayForTree1, int[] arrayForTree2, int index1, int index2, int min, int max)
    {
        /*
         * find the first element between min and max.
         * that element would be used as the root of the subtree we are looking to construct
         */
        int i,j;
        System.out.println("Min : " + min + " , max : " + max + " , i1 : " + index1 + " , i2 : " + index2);
        for (i = index1; i < arrayForTree1.length; i++)
        {
            if ((min < arrayForTree1[i]) && (arrayForTree1[i] < max))
            {
                break;
            }
        }

        for (j = index2; j < arrayForTree2.length; j++)
        {
            if ((min < arrayForTree2[j]) && (arrayForTree2[j] < max))
            {
                break;
            }
        }

        /*
         * since we are constructing same part of the trees for both arrays
         * element found should be same. If element not found,
         * then that means we found no node satisfying this condition.
         * If this is the case then this should be the case for both the trees
         */
        if ((i == arrayForTree1.length) && (j == arrayForTree2.length))
        {
            return true; // no node found for both trees
        }

        if ((i == arrayForTree1.length) || (j == arrayForTree2.length))
        {
            return false; // no node found only in case of one of the trees
        }

        // if current nodes are same then check if left and right sub-trees for current nodes are same
        if (arrayForTree1[i] == arrayForTree2[j])
        {
            System.out.println(arrayForTree1[i] + " , ind1 : " + i + " , ind2 : " + j);
            System.out.println();
            return (checkIfSameBSTs(arrayForTree1, arrayForTree2, i+1, j+1, min, arrayForTree1[i]) &&
                    checkIfSameBSTs(arrayForTree1, arrayForTree2, i+1, j+1, arrayForTree1[i], max));
        }

        return false;
    }

    public boolean checkIfSameBSTs(int[] arrayForTree1, int[] arrayForTree2)
    {
        return checkIfSameBSTs(arrayForTree1, arrayForTree2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args)
    {
        CheckIdenticalBSTs solution = new CheckIdenticalBSTs();

        int[] arrayForTree1 = {3,5,4,6,1,0,2};

        int[] arrayForTree2 = {3,1,5,2,4,6,0};

        System.out.println("Check if identical BSTs: " + solution.checkIfSameBSTs(arrayForTree1, arrayForTree2));
    }
}
