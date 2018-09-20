package trees;

// https://www.careercup.com/question?id=11856466

class Node1
{
    public Node1 parent = null;
    public Node1[] child = new Node1[29];
    public Node1 prev = null;
    public Node1 next = null;
    public char data = ' ';
    public int count = 0;
}

public class UniqueURL {
    public static String firstUnique(String[] array)
    {
        Node1 root = new Node1();
        Node1 first = null;
        Node1 last = null;
        for (String s : array)
        {
            Node1 current = root;
            /* Modify the trie */
            for (char c : s.toCharArray())
            {
                int index = 0;
                if (':' == c) {index = 28;}
                else if ('/' == c) {index = 27;}
                else if ('.' == c) {index = 26;}
                else {index = c - 'a';}

                if (null == current.child[index])
                {
                    Node1 next = new Node1();
                    next.parent = current;
                    next.data = c;
                    current.child[index] = next;
                }
                current = current.child[index];
            }

            /* Modify the linked list */
            current.count++;
            if (1 == current.count)
            {
                if (null == first) {first = last = current;}
                else
                {
                    current.prev = last;
                    last.next = current;
                    last = current;
                }
            }
            else if (2 == current.count)
            {
                if (current == first)
                {
                    first = current.next;
                    if (null != first) {first.prev = null;}
                }
                else
                {
                    Node1 prev = current.prev;
                    prev.next = current.next;
                    if (null != current.next)
                    {
                        current.next.prev = prev;
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        Node1 current = first;
        while (current != root)
        {
            sb.append(current.data);
            current = current.parent;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args)
    {
        String[] urls = {
//                "abc.google.com",
                "abc.facebook.com",
//                "abc.amazon.com",
//                "abc.yahoo.com",
                "abc.facebook.com",
                "abc.yahoo.com",
//                "abc.facebook.com",
                "abc.google.com"
        };
        System.out.println(firstUnique(urls));
    }
}
