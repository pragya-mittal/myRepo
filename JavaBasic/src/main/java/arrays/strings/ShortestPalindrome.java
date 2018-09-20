package arrays.strings;

public class ShortestPalindrome {
    public static void main(String[] args) {
        String palin = "acaba";
//        String palin = "abc";
//        System.out.println(getPalin(palin));
        System.out.println(shortestPal(palin));
    }

    private static String getPalin(String palin) {
        int i=0;
        int last = palin.length()-1;
        int start = 0;
        int end = palin.length()-1;
        while(start<end) {
            if (palin.charAt(start)!=palin.charAt(end)) {
                String str="";
                for (int j=last;j>=(last-start);j--) {
                    str = str + String.valueOf(palin.charAt(j));
                }
                palin = str + palin;
                start=0;end = palin.length()-1;
            } else {
                start++;
                end--;

            }
        }
        return palin;
    }

    public  static String shortestPal(String s)
    {
        String rev_s = new StringBuilder(s).reverse().toString();
        //use special character to avoid overlap
        String l = s + "#" + rev_s;

        int[] p = new int[l.length()];

        //build KMP table.
        //i -> suffix boundary
        //j -> prefix boundary


        for(int i=1; i<l.length(); i++)
        {
            //update prefix boundary to previous match position
            int j = p[i-1];

            //move to the last prefix boundary match
            while(j>0 && l.charAt(i)!=l.charAt(j))
                j = p[j-1];

            //if prefix boundary matches suffix boundary,
            //increase prefix length
            if(l.charAt(i) == l.charAt(j)) p[i] = j + 1;
        }

        return rev_s.substring(0, s.length() - p[l.length() - 1]) + s;
    }

}
