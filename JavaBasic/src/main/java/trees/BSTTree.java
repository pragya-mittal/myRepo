package trees;

public class BSTTree {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    /*
     * Create a sample tree
     *              4
     *      2               6
     * 1        3       5       7
     */
    public void createSampleTree() {
        root = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(6,new Node(5), new Node(7)));
    }

    // Efficient method to find if a binary tree is a BST
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int low, int high) {
        if(root == null) {
            return true;
        }
        if(root.data <= low || root.data >= high) {
            return false;
        }
        System.out.println(root.data + " , " + low + " , " + high);
        return isBinarySearchTree(root.left, low, root.data) &&
                isBinarySearchTree(root.right, root.data, high);
    }

    // Simple method that checks if max value in left sub tree is smaller than current node and min value in right sub tree is greater than the current node, for every node of the tree.
    public boolean isBinarySearchTreeSimple() {
        return isBinarySearchTreeSimple(root);
    }

    private boolean isBinarySearchTreeSimple(Node root) {
        if(root == null) {
            return true;
        }

        if(root.left != null) {
            int max = getMaxValue(root.left);
            if(max > root.data) {
                return false;
            }
        }
        if(root.right != null) {
            int min = getMinValue(root.right);
            if(min < root.data) {
                return false;
            }
        }

        if(!isBinarySearchTreeSimple(root.left) || !isBinarySearchTreeSimple(root.right)) {
            System.out.println("");
            return false;
        }

        return true;
    }

    private int getMinValue(Node root) {
        if(root == null) {
            return Integer.MAX_VALUE;
        }
        return min(root.data, getMinValue(root.left), getMinValue(root.right));
    }

    public int getMaxValue(Node root) {
        if(root == null) {
            return 0;
        }
        return max(root.data, getMaxValue(root.left), getMaxValue(root.right));
    }

    private int max(int n1, int n2, int n3) {
        if(n1 > n2 && n1 > n3) {
            return n1;
        } else if(n2 > n1 && n2 > n3) {
            return n2;
        } else {
            return n3;
        }
    }

    private int min(int n1, int n2, int n3) {
        if(n1 < n2 && n1 < n3) {
            return n1;
        } else if(n2 < n1 && n2 < n3) {
            return n2;
        } else {
            return n3;
        }
    }

    public static void main(String[] args) {
        BSTTree tree = new BSTTree();
        tree.createSampleTree();
        System.out.println(tree.isBinarySearchTree());
    }
}

