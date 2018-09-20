package trees;

public class LevelSiblingOfGivenNode {
    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    int getHeight(Node node, int data, int level) {
        if (node == null)
            return 0;
        if (node.key==data) {
            return level;
        }
        int leftLevel = getHeight(node.left, data, level + 1);
        if (leftLevel!=0)
            return leftLevel;
        int rightLevel = getHeight(node.right, data, level + 1);
        return rightLevel;

    }

    Node getSibling(Node node, int data) {
        if (node == null)
            return null;
        if (node.key == data) {
            return null;
        }
        if (node.right!=null && node.left!=null) {
            if ( node.left.key==data)
                return node.right;
            if (node.right.key==data)
                return node.left;
        }
        Node nodeSibling = getSibling(node.left, data);
        if(nodeSibling!=null)
            return nodeSibling;
        nodeSibling = getSibling(node.right, data);
        if (nodeSibling!=null)
            return nodeSibling;
        return null;
    }

    void getCousins(Node node, int data) {
        int level = getHeight(node, data, 1);
        if (node == null || level<2)
            return;
        getCousins(node, data, level);
    }

    void getCousins(Node node, int data, int level) {
        if (node == null || level<2)
            return;
        if (level==2) {
            if (node.left!=null && node.left.key==data)
                return;
            if (node.right!=null && node.right.key==data)
                return;
            if (node.left!=null)
                System.out.print(node.left.key + " ");
            if (node.right!=null)
                System.out.print(node.right.key + " ");
        }

        getCousins(node.left, data, level-1);
        getCousins(node.right, data, level-1);
    }

    void printNoSiblings(Node node) {
        if (node==null)
            return;
        if (node.left==null && node.right!=null)
            System.out.print(node.right.key + " ");
        if (node.left!=null && node.right==null)
            System.out.print(node.left.key + " ");
        printNoSiblings(node.left);
        printNoSiblings(node.right);
    }

    public void deleteKLessPath(int k) {
        int sum[] = new int[1];
        deleteKLessPath(this.root, sum, k);
        if (sum[0] < k)
            root = null;
    }

    public Node deleteKLessPath(Node node, int[] sum, int k) {
        if (node == null)
            return null;

        int[] ls = new int[1];
        int[] rs = new int[1];
        ls[0] = rs[0] = sum[0] + node.key;

        node.left = deleteKLessPath(node.left, ls, k);
        node.right = deleteKLessPath(node.right, rs, k);

        sum[0] = ls[0] > rs[0] ? ls[0] : rs[0];
        if (sum[0] < k) {
            node = null;
        }
        return node;
    }

    void inorder(Node node) {
        if (node==null)
            return;
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        LevelSiblingOfGivenNode treeHeight = new LevelSiblingOfGivenNode();
        treeHeight.root = new Node(1);
        treeHeight.root.left = new Node(2);
        treeHeight.root.left.left = new Node(4);
        treeHeight.root.left.left.left = new Node(8);
        treeHeight.root.left.right = new Node(5);
        treeHeight.root.right = new Node(3);
        treeHeight.root.right.left = new Node(6);
        treeHeight.root.right.left.left = new Node(9);
        treeHeight.root.right.right = new Node(7);
        treeHeight.root.right.right.right = new Node(10);
        treeHeight.root.right.right.right.left = new Node(11);

        //Level
        System.out.println("Height = " + treeHeight.getHeight(treeHeight.root, 10, 1));
        System.out.println("Height = " + treeHeight.getHeight(treeHeight.root, 15, 1));

        // Siblings
        Node sibling = treeHeight.getSibling(treeHeight.root, 6);
        if (sibling!=null)
            System.out.println(sibling.key);

        //Cousins
        System.out.println("Cousin : ");
        treeHeight.getCousins(treeHeight.root, 4);
        System.out.println();
        treeHeight.getCousins(treeHeight.root, 8);

        // All nodes which do not have siblings
        System.out.println("\nSiblings : ");
        treeHeight.printNoSiblings(treeHeight.root);

        System.out.println("\norder : ");
        treeHeight.inorder(treeHeight.root);

        //delete k less path
        treeHeight.deleteKLessPath(16);

        System.out.println("\nKorder : ");
        treeHeight.inorder(treeHeight.root);



//        LevelSiblingOfGivenNode treeHeight1 = new LevelSiblingOfGivenNode();
//        treeHeight1.root = new Node(3);
//        treeHeight1.root.right = new Node(5);
//        treeHeight1.root.left = new Node(2);
//        treeHeight1.root.left.left = new Node(1);
//        treeHeight1.root.left.right = new Node(4);
//        //Level
//        System.out.println("\nHeight : " +treeHeight1.getHeight(treeHeight1.root, 4, 1));
//
//        // Siblings
//        sibling = treeHeight1.getSibling(treeHeight1.root, 5);
//        if (sibling!=null)
//            System.out.println(sibling.key);





    }
}
