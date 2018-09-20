package arrays.strings;

public class LongestLengthPalindrome {
    public static void main(String[] args) {

//        String str = "forgeeksskeegfor";
//        String str = "eksskeegfor";
        String str = "abcb";
        longestPalSubstr(str);
        longestPalSubstrevenodd(str);
        manacher(str);
    }

    //DP
    private static void longestPalSubstr(String str) {
        int n = str.length();
        int maXlength = 0;
        int start = 0;
        boolean[][] palMat = new boolean[n][n];

        for (int i=0;i<n;i++) {
            palMat[i][i] = true;
            maXlength=1;
            start=i;
        }

        for (int i=0;i<n-1;i++) {
            if(str.charAt(i) == str.charAt(i+1)) {
                palMat[i][i+1] = true;
                maXlength=2;
                start=i;
            }
        }

        for (int k=3;k<=n;k++) {
            for (int i=0;i<n-k;i++) {
                int j = i+k-1;

                if (str.charAt(i)==str.charAt(j) && palMat[i+1][j-1]) {
                    palMat[i][j] = true;
                    if (maXlength<k) {
                        maXlength=k;
                        start=i;
                    }
                }
            }
        }

        System.out.println("Max length is : " + str.substring(start, start+maXlength));
    }

    // even odd
    private static void longestPalSubstrevenodd(String str) {
        int n = str.length();
        int max=1;
        int start=0;
        int low,high;

        for (int i=1;i<n;i++) {
            //even
            low=i-1;high=i;
            while(low>=0 && high<n && (str.charAt(low) == str.charAt(high)) ) {
                if (high-low + 1 > max) {
                    max=high-low+1;
                    start=low;
                }
                low--;high++;
            }

            //odd
            low = i-1; high=i+1;
            while(low>=0 && high<n && (str.charAt(low) == str.charAt(high)) ) {
                if (high-low + 1 > max) {
                    max=high-low+1;
                    start=low;
                }
                low--;high++;
            }

        }
        System.out.println("Max length is : " + str.substring(start, start+max));

    }

    public static void manacher(String s) {

        char[] T = new char[s.length()*2 + 3];
        T[0] = '$';
        T[s.length()*2 + 2] = '@';
        for (int i = 0; i < s.length(); i++) {
            T[2*i + 1] = '#';
            T[2*i + 2] = s.charAt(i);
        }
        T[s.length()*2 + 1] = '#';


        int[]  P = new int[T.length];
        int center = 0, right = 0;

        for (int i = 1; i < T.length-1; i++) {
            int mirr = 2*center - i;

            if (i < right)
                P[i] = Math.min(right - i, P[mirr]);

            while (T[i + (1 + P[i])] == T[i - (1 + P[i])])
                P[i]++;

            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
        }

        int length = 0;   // length of longest palindromic substring
        center = 0;   // center of longest palindromic substring
        for (int i = 1; i < P.length-1; i++) {
            if (P[i] > length) {
                length = P[i];
                center = i;
            }
        }
        System.out.println(s.substring((center - 1 - length) / 2, (center - 1 + length) / 2));

    }

}
