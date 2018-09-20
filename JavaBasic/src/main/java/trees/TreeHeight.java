package trees;

public class TreeHeight {
    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    int getHeight(Node node) {
        if (node == null)
            return 0;
        int height =1;
        int treeheight = height + Math.max(getHeight(node.left), getHeight(node.right));
        return treeheight;
    }

    int getMinHeight(Node node) {
        if (node==null)
            return 0;
        int height = 1;
        int minHeight = height + Math.min(getMinHeight(node.left), getMinHeight(node.right));
        return minHeight;
    }

    int depthDeepestOddLevelLeafNode(Node node) {
        return depthDeepestOddLevelLeafNode(node, 1);
    }

    int depthDeepestOddLevelLeafNode(Node node, int height) {
        if (node==null)
            return 0;
        if (node.left==null && node.right==null && (height%2==1)) {
            System.out.println("Node : " + node.key + " , hei : " + height);
            return height;
        }
        return Math.max(depthDeepestOddLevelLeafNode(node.left, height+1), depthDeepestOddLevelLeafNode(node.right, height+1));
    }


    public static void main(String[] args) {
        TreeHeight treeHeight = new TreeHeight();
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

        System.out.println("Height = " + treeHeight.getHeight(treeHeight.root));
        System.out.println("Min Height = " + treeHeight.getMinHeight(treeHeight.root));

        System.out.println("Odd deep left node height is : " + treeHeight.depthDeepestOddLevelLeafNode(treeHeight.root));


        TreeHeight treeHeight1 = new TreeHeight();
        treeHeight1.root = new Node(3);
        treeHeight1.root.right = new Node(5);
        treeHeight1.root.left = new Node(2);
        treeHeight1.root.left.left = new Node(1);
        treeHeight1.root.left.right = new Node(4);
        System.out.println("Height : " +treeHeight1.getHeight(treeHeight1.root));
        System.out.println("Min Height : " +treeHeight1.getMinHeight(treeHeight1.root));



    }
}
