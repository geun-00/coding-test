import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node root = new Node("");

        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            Node cur = root;

            for (int j = 0; j < k; j++) {
                String food = st.nextToken();

                if (!cur.children.containsKey(food)) {
                    cur.children.put(food, new Node(food));
                }

                cur = cur.children.get(food);
            }
        }

        printResult(root, 0);
        System.out.println(sb);
    }

    private static void printResult(Node node, int depth) {

        for (Node child : node.children.values()) {

            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }

            sb.append(child.name).append("\n");

            printResult(child, depth + 1);
        }
    }

    static class Node {
        String name;
        TreeMap<String, Node> children;

        public Node(String name) {
            this.name = name;
            this.children = new TreeMap<>();
        }
    }
}
