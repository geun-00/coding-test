import java.util.Arrays;

class Solution {
    
    static int[] preOrder, postOrder;
    static int order;
    
    public int[][] solution(int[][] nodeinfo) {
        
        int n = nodeinfo.length;
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodes, (a, b) -> b.y - a.y);

        Node root = buildTree(nodes);

        order = 0;
        preOrder = new int[n];
        getPreOrder(root);

        order = 0;
        postOrder = new int[n];
        getPostOrder(root);

        return new int[][]{
                preOrder, postOrder
        };
    }
    
    public void getPostOrder(Node node) {
        
        if (node == null) return;

        getPostOrder(node.left);
        getPostOrder(node.right);
        postOrder[order++] = node.num;
    }

    public void getPreOrder(Node node) {

        if (node == null) return;

        preOrder[order++] = node.num;
        getPreOrder(node.left);
        getPreOrder(node.right);
    }
    
    public Node buildTree(Node[] nodes) {

        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }

        return root;
    }
    
    public void insert(Node parent, Node node) {
        
        if (node.x < parent.x) {
            if (parent.left == null) {
                parent.left = node;
            } else {
                insert(parent.left, node);
            }
        } 
        else {
            if (parent.right == null) {
                parent.right = node;
            } else {
                insert(parent.right, node);
            }
        }
    }
    
    static class Node {

        int num, x, y;
        Node left, right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}