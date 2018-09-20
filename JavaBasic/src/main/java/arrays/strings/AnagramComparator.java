package arrays.strings;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramComparator implements Comparator<String> {

    public static void main(String[] args)
    {
        String[] strArray = {"abcd","abc","abce", "acd","abdc"};

        Arrays.sort(strArray, new AnagramSort());

        for (int i = 0; i < strArray.length; i++)
        {
            System.out.println(strArray[i]);
        }
    }


    public int compare(String  o1, String  o2) {
        Arrays.sort(o1.toCharArray());
        Arrays.sort(o2.toCharArray());
        return o1.compareTo(o2);
    }
}