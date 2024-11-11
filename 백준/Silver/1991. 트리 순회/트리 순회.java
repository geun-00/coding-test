import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node[] tree = new Node[26];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (tree[node - 'A'] == null) {
                tree[node - 'A'] = new Node(node);
            }

            Node now = tree[node - 'A'];

            if (left != '.') {
                now.left = new Node(left);
                tree[left - 'A'] = now.left;
            }
            if (right != '.') {
                now.right = new Node(right);
                tree[right - 'A'] = now.right;
            }
        }

        preOrder(tree[0]);
        sb.append("\n");

        inOrder(tree[0]);
        sb.append("\n");

        postOrder(tree[0]);

        System.out.println(sb);
    }

    private static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        sb.append(node.name);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        sb.append(node.name);
        inOrder(node.right);
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.name);
    }

    static class Node {
        char name;
        Node left, right;

        public Node(char name) {
            this.name = name;
        }
    }
}