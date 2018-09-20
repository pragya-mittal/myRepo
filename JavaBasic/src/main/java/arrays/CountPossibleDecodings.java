package arrays;

public class CountPossibleDecodings {
    private boolean validDecoding(int digit0, int digit1)
    {
        if ((10*digit0 + digit1) >= 10 && ((10*digit0 + digit1) < 27))
        {
            return true;
        }

        return false;
    }

    public int countPossibleDecodings(int n, int[] digitSequence, int[] decodings)
    {
        // n denotes the length of the digit array that's already been used

        // if n is (digit.length - 1) then all the digits are already used
        // if n is (digit.length - 2) then all the digits except the last one are already used
        // in both cases only one valid decoded sequence is possible
        if (n == digitSequence.length - 1 || n == digitSequence.length - 2)
        {
            // store the computed value to avoid re-computations
            // need to consider special case when we have sequence of length 0 or length 1
            if (n != -1)
            {
                decodings[n] = 1;
            }

            return 1;
        }

        int count = 0;

        // count number of decodings by using digit at index 'n+1'
        if (digitSequence[n+1] > 0)
        {
            count = (decodings[n+1] != 0) ? decodings[n+1] :
                    countPossibleDecodings(n+1, digitSequence, decodings);
        }

        // count number of decodings by using next two digits at once
        if (validDecoding(digitSequence[n+1], digitSequence[n+2]))
        {
            count += (decodings[n+2] != 0) ? decodings[n+2] :
                    countPossibleDecodings(n+2, digitSequence, decodings);
        }

        // store the computed value to avoid re-computations
        // need to consider special case with n = -1 for the initial call
        if (n != -1)
        {
            decodings[n] = count;
        }

        return count;
    }


    public int countPossibleDecodings(int n, int[] digitSequence)
    {
        // n denotes the length of the digit array that's already been used

        // if n is (digit.length - 1) then all the digits are already used
        // if n is (digit.length - 2) then all the digits except the last one are already used
        // in both cases only one valid decoded sequence is possible
        if (n == digitSequence.length - 1 || n == digitSequence.length - 2)
        {
            return 1;
        }

        int count = 0;

        // count number of decodings by using digit at index 'n+1'
        if (digitSequence[n+1] > 0)
        {
            count = countPossibleDecodings(n+1, digitSequence);
        }

        // count number of decodings by using next two digits at once
        if (validDecoding(digitSequence[n+1], digitSequence[n+2]))
        {
            count += countPossibleDecodings(n+2, digitSequence);
        }

        return count;
    }


    public static void main(String[] args)
    {
        int[] digit = {1,2,2,3};

        CountPossibleDecodings solution = new CountPossibleDecodings();

        int[] decodings = new int[digit.length];
        System.out.println("number of possible decodings:\n"+solution.countPossibleDecodings(-1, digit, decodings));
    }
}
