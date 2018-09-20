package trees;

public class ParentTree {
    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node getParent(Node node, int childData) {
        if (node == null)
            return null;

        if (node.left!=null && node.left.key==childData)
            return node;
        else if (node.right!=null && node.right.key==childData)
            return node;
        else if(getParent(node.left, childData)==null){
            return getParent(node.right, childData);
        } else {
            return getParent(node.left, childData);
        }
    }

    public static void main(String[] args) {
        ParentTree treeHeight = new ParentTree();
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

        Node node = treeHeight.getParent(treeHeight.root, 4);
        if (node !=null)
            System.out.println(node.key);
        else
            System.out.println("Node not present");

        node = treeHeight.getParent(treeHeight.root, 13);
        if (node !=null)
            System.out.println(node.key);
        else
            System.out.println("Node not present");



    }
}
