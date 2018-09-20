package trees;

public class PreInorderTree {
    static class Node
    {
        int key;
        Node left, right;

        public Node(int key)
        {
            this.key = key;
            left = right = null;
        }
    }

    Node root;
    private void printInorder(Node root)
    {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(" "+ root.key + ",");
        printInorder(root.right);
    }

    private void printPreorder(Node root)
    {
        if (root == null) return;

        System.out.print(" "+ root.key + ",");
        printPreorder(root.left);
        printPreorder(root.right);

    }

    private void printPostorder(Node root)
    {
        if (root == null) return;

        printPreorder(root.left);
        printPreorder(root.right);
        System.out.print(" "+ root.key + ",");

    }

    private int searchInorder(int[] inorder, int val, int inStart, int inEnd) {
        for (int i=inStart;i<=inEnd;i++) {
            if (inorder[i]==val)
                return i;
        }
        return -1;

    }

    Node createInorderPreorderTree (int[] inorder, int inStart, int inEnd, int[] preorder, int prestart, int preend) {
        if ((inStart>inEnd) || (prestart>preend))
            return null;
        Node root = new Node(preorder[prestart]);

        int divideIndex = searchInorder(inorder, preorder[prestart], inStart, inEnd);
        int leftSize = divideIndex-inStart;
        int rightSize = inEnd-divideIndex;

        root.left = createInorderPreorderTree(inorder, inStart, divideIndex-1, preorder, prestart+1, prestart+leftSize);
        root.right = createInorderPreorderTree(inorder, divideIndex+1, inEnd, preorder, prestart+leftSize+1, prestart+leftSize+rightSize);

        return root;

    }

    Node createInorderPostorderTree (int[] inorder, int inStart, int inEnd, int[] postorder, int poststart, int postend) {
        if ((inStart>inEnd) || (poststart>postend))
            return null;
        Node root = new Node(postorder[postend]);

        int divideIndex = searchInorder(inorder, postorder[postend], inStart, inEnd);
        int leftSize = divideIndex-inStart;
        int rightSize = inEnd-divideIndex;

        root.right = createInorderPreorderTree(inorder, divideIndex+1, inEnd, postorder, postend-rightSize, postend-1);
        root.left = createInorderPreorderTree(inorder, inStart, divideIndex-1, postorder, postend-rightSize-leftSize, postend-rightSize-1);
        return root;

    }

    private Node buildTreeFromPre(int[] preorder, int[] inorder) {
        return createInorderPreorderTree(inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
    }

    private Node buildTreeFromPost(int[] postorder, int[] inorder) {
        return createInorderPostorderTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    public static void main(String[] args)
    {
        PreInorderTree solution = new PreInorderTree();

        /*
                1
          2            3
        4   5        6    7
        */

        int[] inorder = {4,2,5,1,6,3,7};
        int[] preorder = {1,2,4,5,3,6,7};
        int[] postorder = {2,4,5,3,6,7,1};

        Node node = solution.buildTreeFromPre(preorder, inorder);
        System.out.print("Inorder array is:");
        solution.printInorder(node);

        System.out.println("");

        System.out.print("preorder array is:");
        solution.printPreorder(node);

        Node nodeNew = solution.buildTreeFromPost(postorder, inorder);
        System.out.println("");
        System.out.print("Inorder array is:");
        solution.printInorder(nodeNew);

        System.out.println("");

        System.out.print("postorder array is:");
        solution.printPostorder(nodeNew);
    }


}
