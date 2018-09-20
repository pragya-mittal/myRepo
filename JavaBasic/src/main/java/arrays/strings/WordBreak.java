package arrays.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
    private static final Set<String> dictionary = new HashSet<String>(Arrays.asList("arrays", "dynamic", "heaps", "IDeserve", "learn", "learning", "linked", "list", "platform", "programming", "stacks", "trees"));

    public static void main(String[] args) {
        String str = "learningIDeservelearningplatform";
        if (hasValidWord(str))
            System.out.println("true");
        else
            System.out.println("false");
    }

    private static boolean hasValidWord(String str) {
        boolean flag = false;
        int start=0;
        boolean[] valid = new boolean[str.length()];
        for (int i=0;i<str.length();i++) {
            if (dictionary.contains(str.substring(0,i+1))) {
                valid[i]=true;
            }
            if ((valid[i]) && (i==str.length()-1) )
                return true;
            if (valid[i]) {
                for (int j=i+1;j<str.length();j++) {
                    if (dictionary.contains(str.substring(i+1,j+1))) {
                        valid[j]=true;
                        if ((valid[j]) && (j==str.length()-1) )
                            return true;
                    }
                }
            }
        }
        return false;

    }

//    private static boolean hasValidWords(String str) {
//        boolean flag = false;
//        int start=0;
//        for (int i=0;i<str.length();i++) {
//            if (dictionary.contains(str.substring(start,i+1))) {
//                start=i+1;
//                flag=true;
//            } else {
//                flag=false;
//            }
//        }
//        return flag;
//
//    }

}
