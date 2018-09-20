package arrays;

public class MaximumfromEachSubarray
{
    private class AVLTreeNode
    {
        int data;
        AVLTreeNode left;
        AVLTreeNode right;
        int height;

        AVLTreeNode(int data)
        {
            this.data = data;
            this.height = 1;
        }
    }

    AVLTreeNode root;

    public MaximumfromEachSubarray()
    {

    }

    private int getHeight(AVLTreeNode node)
    {
        if (node == null)
            return 0;

        return node.height;
    }

    private void updateHeight(AVLTreeNode node)
    {
        if (node == null) return;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }


    private AVLTreeNode rotateRight(AVLTreeNode node)
    {
        if (node == null) return node;

        AVLTreeNode beta  = node.left;

        AVLTreeNode t2  = beta.right;

        beta.right = node;
        node.left = t2;

        // we first need to update the height of node because height of beta uses height of node
        updateHeight(node);

        // now we update height of beta
        updateHeight(beta);

        return beta;
    }


    private AVLTreeNode rotateLeft(AVLTreeNode node)
    {
        if (node == null) return node;

        AVLTreeNode beta  = node.right;
        AVLTreeNode t2  = beta.left;

        beta.left = node;
        node.right = t2;

        // we first need to update the height of node because height of beta uses height of node
        updateHeight(node);

        // now we update height of beta
        updateHeight(beta);

        return beta;
    }


    private int getBalance(AVLTreeNode node)
    {
        if (node == null)
        {
            return 0;
        }
        int balance;

        balance = getHeight(node.left) - getHeight(node.right);

        return balance;
    }


    private int getMaxValue(AVLTreeNode node)
    {
        // though this case should not be hit ever for the usecase this function is employed for
        if (node == null) return Integer.MIN_VALUE;

        // if this is the left-most node
        if (node.right == null) return node.data;

        return getMaxValue(node.right);
    }

    private int getMinValue(AVLTreeNode node)
    {
        // though this case should not be hit ever for the usecase this function is employed for
        if (node == null) return Integer.MIN_VALUE;

        // if this is the left-most node
        if (node.right == null) return node.data;

        return getMaxValue(node.left);
    }

    private AVLTreeNode delete(AVLTreeNode node, int key)
    {
        // if empty tree
        if (node == null) return null;

        if (key < node.data)
        {
            node.left = delete(node.left, key);
        }
        else if (key > node.data)
        {
            node.right = delete(node.right, key);
        }

        else // key to be deleted is equal to node data
        {
            // one child/no child case
            if (node.left == null)
            {
                node = node.right;
            }
            else if (node.right == null)
            {
                node = node.left;
            }

            // two children case
            // copy value of inorder successor into current node and delete inorder successor
            // since right sub-tree would be modified, update node.right
            else
            {
                int inorderSuccessorValue = getMinValue(node.right);
                node.data = inorderSuccessorValue;
                node.right = delete(node.right, inorderSuccessorValue);
            }
        }

        // if there was only one node in the tree which got deleted above return null
        if (node == null)
        {
            return null;
        }

        // update the height of the node
        updateHeight(node);

        // check the balance at this node and perform rotations accordingly
        int balance = getBalance(node);

        if (balance > 1) // indicates either left-left or left-right case
        {
            if (getBalance(node.left) >= 0) // confirms left-left case
            {
                node = rotateRight(node);
            }
            else // confirms left-right case
            {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        else if (balance < -1) // indicates either right-right or right-left case
        {
            if (getBalance(node.right) <= 0) // confirms right-right case
            {
                node = rotateLeft(node);
            }
            else // confirms right-left case
            {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        return node;
    }


    private AVLTreeNode insert(AVLTreeNode node, int key)
    {
        // do usual BST insertion first
        if (node == null)
        {
            this.root =  new AVLTreeNode(key);
            return this.root;
        }

        if (key < node.data)
        {
            node.left = insert(node.left, key);
        }
        else if (key > node.data)
        {
            node.right = insert(node.right, key);
        }
        else
        {
            return node;
        }

        // now update the height of the node
        updateHeight(node);

        // check the balance at this node and perform rotations accordingly
        int balance = getBalance(node);

        if (balance > 1) // indicates either left-left or left-right case
        {
            if (key < node.left.data) // confirms left-left case
            {
                node = rotateRight(node);
            }
            else // confirms left-right case
            {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        else if (balance < -1) // indicates either right-right or right-left case
        {
            if (key > node.right.data) // confirms right-right case
            {
                node = rotateLeft(node);
            }
            else // confirms right-left case
            {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        return node;
    }


    public void insert(int key)
    {
        root = insert(this.root, key);
        return;
    }


    public void delete(int key)
    {
        root = delete(this.root, key);
        return;
    }


    private void printMax(int[] array, int low, int high)
    {
        int maxValue = Integer.MIN_VALUE;
        for (int i = low; i <= high; i++)
        {
            if (array[i] > maxValue)
            {
                maxValue = array[i];
            }
        }

        System.out.println(maxValue);
    }

    public void simplePrintMaxfromEachSubarray(int[] array, int k)
    {
        // {4,2,12,34,23,35,44,55};

        int low = 0, high = low + k - 1;

        while (high < array.length)
        {
            printMax(array, low, high);
            low  += 1;
            high += 1;
        }
    }

    public void printMaxfromEachSubarray(int[] array, int k)
    {
        // create a balanced BST by inserting first 'k' keys
        for (int i = 0; i < k; i++)
        {
            insert(array[i]);
        }
        // print the maximum value of first sub-array of 'k' elements
        System.out.println(getMaxValue(root));

        /*
         *  Now find maximum element for each remaining contiguous sub-array of size 'k'
         *  1. delete 'i'th element from BST
         *  2. insert (i+k)th element into BST
         *  3. find element with maximum value in this modified BST
         */
        for (int i = 0; i < array.length-k; i++)
        {
            delete(array[i]);
            insert(array[i+k]);
            System.out.println(getMaxValue(root));
        }
    }

    public static void main(String[] args)
    {
        MaximumfromEachSubarray solution = new MaximumfromEachSubarray();

        int[] array = {4,2,12,34,23,35,44,55};
        int k = 3;

        System.out.println("Maximum elements from each sub-array of specified size are - ");
        solution.printMaxfromEachSubarray(array, k);
    }
}
