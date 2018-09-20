package trees;

public class CeilingFloorTree {

    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node ceiling = null;
    Node floor = null;
    Node value = null;

    // wrong
    void getFloorCeiling(Node node, int data) {
        if (node==null)
            return;
        getFloorCeiling(node.left, data);

        if (node.key==data) {
            ceiling =node;
            floor = node;
        } else if (value!=null && node.key > data) {
            ceiling = node;
            floor = value;
        } else {
            floor = node;
            ceiling = null;
        }
        value = node;

        getFloorCeiling(node.right, data);

    }


    public static void main(String[] args) {
        CeilingFloorTree treeHeight = new CeilingFloorTree();
        treeHeight.root = new Node(6);
        treeHeight.root.left = new Node(2);
        treeHeight.root.left.left = new Node(0);
        treeHeight.root.left.right = new Node(5);
        treeHeight.root.right = new Node(8);
        treeHeight.root.right.right = new Node(12);

        treeHeight.getFloorCeiling(treeHeight.root, 11);
        System.out.println("Floor : " + treeHeight.floor.key);
        System.out.println("Ceiling : " + treeHeight.ceiling.key);

        treeHeight.getFloorCeiling(treeHeight.root, 12);
        System.out.println("Floor : " + treeHeight.floor.key);
        System.out.println("Ceiling : " + treeHeight.ceiling.key);

        treeHeight.getFloorCeiling(treeHeight.root, 13);
        System.out.println("Floor : " + treeHeight.floor.key);
        System.out.println("Ceiling : " + treeHeight.ceiling.key);




    }
}
