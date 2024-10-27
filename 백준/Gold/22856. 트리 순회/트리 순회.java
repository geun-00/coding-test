import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Node[] tree;
    static int count = 0;
    static int lastNode;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        tree = new Node[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new Node(-1, -1, -1);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].left = b;
            tree[a].right = c;

            if (b != -1) tree[b].parent = a;
            if (c != -1) tree[c].parent = a;
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        findLastNode(1);

        dfs(1);
        System.out.println(count);
    }

    private static void findLastNode(int node) {
        if (node == -1) return;
        findLastNode(tree[node].left);
        lastNode = node;
        findLastNode(tree[node].right);
    }

    private static void dfs(int node) {
        visit[node] = true;

        if (tree[node].left != -1 && !visit[tree[node].left]) {
            count++;
            dfs(tree[node].left);
        }
        else if (tree[node].right != -1 && !visit[tree[node].right]) {
            count++;
            dfs(tree[node].right);
        }
        else if (node != lastNode) {
            count++;
            dfs((tree[node].parent));
        }
    }

    static class Node {
        int parent, left, right;

        public Node(int parent, int left, int right) {
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
