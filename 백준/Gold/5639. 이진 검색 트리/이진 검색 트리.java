import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int r = Integer.parseInt(br.readLine());

        Node root = new Node(r);

        while (true) {
            String s = br.readLine();

            if (s == null || s.isEmpty()) {
                break;
            }

            root.link(Integer.parseInt(s));
        }

        postOrder(root);
        System.out.println(sb);
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.root).append('\n');
    }

    static class Node {
        int root;
        Node left;
        Node right;

        public Node(int root) {
            this.root = root;
        }

        public void link(int num) {
            if (num < root) {
                if (left == null) {
                    left = new Node(num);
                } else {
                    left.link(num);
                }
            } else {
                if (right == null) {
                    right = new Node(num);
                } else {
                    right.link(num);
                }
            }
        }
    }
}
