package trees;

public class TrieData {
    static final int ALPHABET_SIZE = 26;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i=0;i<ALPHABET_SIZE;i++)
                children[i]=null;
        }
    }

    static TrieNode root;

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};

        root = new TrieNode();

        for (int i=0;i<keys.length;i++)
            insert(keys[i]);

        // Search for different keys
        if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);


    }

    private static boolean search(String key) {
        int level;
        int len = key.length();
        TrieNode current = root;

        for (level = 0; level<len; level++) {
            int val = key.charAt(level) - 'a';
            if (current.children[val] == null)
                return false;
            current = current.children[val];
        }

        if (current!=null)
            return current.isEndOfWord;
        return false;
    }

    private static void insert(String key) {
        if (key.length()<0)
            return;

        int level;
        int lengthKey = key.length();
        TrieNode current = root;


        for (level=0;level<lengthKey;level++) {
            int val = key.charAt(level) - 'a';

            if (current.children[val] == null) {
                current.children[val] = new TrieNode();
            }
            current = current.children[val];
        }

        current.isEndOfWord = true;
    }
}
