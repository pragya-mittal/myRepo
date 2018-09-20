package arrays.strings;

import java.util.HashSet;

public class Perms {
    public static void main(String[] args) {
        String str = "abc";
        System.out.println(possibleperms(str, 0, str.length()));

    }

    private static HashSet<String> possibleperms(String str, int start, int end) {

        HashSet<String> stringlist = new HashSet<String>();
        if (start==end) {
            stringlist.add("");
            return stringlist;
        }

        char insert = str.charAt(start);
        HashSet<String> words = possibleperms(str, start+1, end);

        for (String word : words) {
            for (int i=0;i<=word.length();i++) {
                String prefix = word.substring(0,i);
                String suffix = word.substring(i);
                stringlist.add(prefix+insert+suffix);
            }
        }

        return stringlist;
    }
}
