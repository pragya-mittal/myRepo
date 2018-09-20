package arrays.strings;

public class PalindromeMinCut {
    static int minPalPartion(String str)
    {
        // Get the length of the string
        int n = str.length();

        /* Create two arrays to build the solution
           in bottom up manner
           C[i][j] = Minimum number of cuts needed
                     for palindrome partitioning
                     of substring str[i..j]
           P[i][j] = true if substring str[i..j] is
                     palindrome, else false
           Note that C[i][j] is 0 if P[i][j] is
           true */
        int[][] C = new int[n][n];
        boolean[][] P = new boolean[n][n];

        int i, j, k, L; // different looping variables

        // Every substring of length 1 is a palindrome
        for (i = 0; i < n; i++)
        {
            P[i][i] = true;
            C[i][i] = 0;
        }

        /* L is substring length. Build the solution in
         bottom up manner by considering all substrings
         of length starting from 2 to n. The loop
         structure is same as Matrx Chain Multiplication
         problem (
        See https://www.geeksforgeeks.org/archives/15553 )*/
        for (L = 2; L <= n; L++)
        {
            // For substring of length L, set different
            // possible starting indexes
            for (i = 0; i < n - L + 1; i++)
            {
                j = i + L - 1; // Set ending index

                // If L is 2, then we just need to
                // compare two characters. Else need to
                // check two corner characters and value
                // of P[i+1][j-1]
                if (L == 2)
                    P[i][j] = (str.charAt(i) ==
                            str.charAt(j));
                else
                    P[i][j] = (str.charAt(i) ==
                            str.charAt(j)) && P[i+1][j-1];

                // IF str[i..j] is palindrome, then
                // C[i][j] is 0
                if (P[i][j] == true)
                    C[i][j] = 0;
                else
                {
                    // Make a cut at every possible
                    // localtion starting from i to j,
                    // and get the minimum cost cut.
                    C[i][j] = Integer.MAX_VALUE;
                    for (k = i; k <= j - 1; k++) {
                        System.out.print(i + "," + k+ " = " +C[i][k] + "," + C[k+1][j]);
                        C[i][j] = Math.min(C[i][j],
                                C[i][k] + C[k+1][j] + 1);
                    }
                    System.out.println();
                }
            }
        }

        // Return the min cut value for complete
        // string. i.e., str[0..n-1]
        return C[0][n-1];
    }

    public static int partition(String s) {
        int n = s.length();
        boolean palindrome[][] = new boolean[n][n]; //boolean table

        //Trivial case: single letter palindromes
        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
        }

        //Finding palindromes of two characters.
        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                palindrome[i][i+1] = true;
            }
        }

        //Finding palindromes of length 3 to n
        for (int curr_len = 3; curr_len <= n; curr_len++) {
            for (int i = 0; i < n-curr_len+1; i++) {
                int j = i+curr_len-1;
                if (s.charAt(i) == s.charAt(j) //1. The first and last characters should match
                        && palindrome[i+1][j-1]) //2. Rest of the substring should be a palindrome
                {
                    palindrome[i][j] = true;
                }
            }
        }

        int[] cuts = new int[n];
        for(int i=0; i<n; i++)
        {
            int temp = Integer.MAX_VALUE;
            if(palindrome[0][i])
                cuts[i] = 0;
            else
            {
                for(int j=0; j<i; j++)
                {
                    if((palindrome[j+1][i]) && temp > cuts[j] + 1)
                    {
                        temp = cuts[j] + 1;
                    }
                }
                cuts[i] = temp;
            }
        }
        return cuts[n-1];
    }

    // Driver program to test above function
    public static void main(String args[])
    {
//        String str = "ababbbabbababa";
//        String str = "babd";
        String str = "bananna";
        System.out.println("Min cuts needed for "+
                "Palindrome Partitioning is "+
                minPalPartion(str));
        partition(str);
    }
}
