package arrays.strings;

public class NonRepeatingCharacter {
    public static void main(String[] args) {
        String str = "ABCDABDEFGCABD";
        /*String longestSubstring = getLongestSubstringNonRepeatingCharsNaive(str);
        System.out.println("Longest substring non repeating chars by Naive method:\t\t" + longestSubstring);*/
        int longestSubstring = nonRepeatingChar(str);
        System.out.println("Longest substring non repeating chars by linear time method: " + str.charAt(longestSubstring));

    }

    private static int nonRepeatingChar(String str) {
        int[] chars = new int[256];
        for (int i=0;i<256;i++)
            chars[i]=-1;

        int pos=-1;

        for (int i=0;i<str.length();i++) {
            int x=chars[str.charAt(i)];
            if (x==-1) {
               chars[str.charAt(i)] = 1;
            } else {
                chars[str.charAt(i)] = chars[str.charAt(i)] + 1;
            }
        }

        for (int i=0;i<str.length();i++) {
            int x=chars[str.charAt(i)];
            if (x==1) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    private static int nonRepeatingCharEfficient(String str) {
        int[] chars = new int[256];
        for (int i=0;i<256;i++)
            chars[i]=-1;

        int pos=-1;

        for (int i=0;i<str.length();i++) {
            int index;
            int x=chars[str.charAt(i)];
            if (x==-1) {
                chars[str.charAt(i)] = i;
            } else {
                chars[str.charAt(i)] = chars[str.charAt(i)] + 1;
            }
        }

        for (int i=0;i<str.length();i++) {
            int x=chars[str.charAt(i)];
            if (x==1) {
                pos = i;
                break;
            }
        }

        return pos;
    }
}
