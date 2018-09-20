package arrays.strings;

import java.util.HashSet;

public class Permutations {
    public static void main(String[] args) {
        String str = "abc";
//        getAllPermutation(str);

        HashSet<String> permutations = getAllPermutations(str);
        System.out.println(permutations.toString());
    }

    private static void getAllPermutation(String str) {
        for (int i=0;i<str.length();i++) {
            for (int j=0;j<str.length();j++) {
                for (int k=0;k<str.length();k++) {
                    if (i==j || j==k || k==i)
                        continue;
                    else
                        System.out.println(str.charAt(i) + "" + str.charAt(j)  + "" + str.charAt(k));
                }
            }
        }
    }

    public static HashSet<String> getAllPermutations(String str) {
        // Create a hash set to prevent any duplicate entries
        HashSet<String> permutations = new HashSet<String>();

        if(str == null || str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String remainingString = str.substring(1);
        HashSet<String> words = getAllPermutations(remainingString);
        for(String word: words) {
            for(int i = 0; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(i);
                permutations.add(prefix + first + suffix);
            }
        }
        return permutations;
    }
}
