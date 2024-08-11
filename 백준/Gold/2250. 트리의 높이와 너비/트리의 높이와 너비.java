import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Node[] tree;
    static Level[] level;
    static int order = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new Node[n + 1];
        level = new Level[n + 1];

        for (int i = 1; i <= n; i++) {
            level[i] = new Level(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        boolean[] child = new boolean[n + 1];

        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            tree[node] = new Node(left, right);

            if (left != -1) {
                child[left] = true;
            }
            if (right != -1) {
                child[right] = true;
            }
        }

        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (!child[i]) {
                root = i;
                break;
            }
        }

        inOrder(root, 1);

        int width = Integer.MIN_VALUE;
        int lv = 0;

        for (int i = 1; i <= n; i++) {

            if (level[i].min == Integer.MAX_VALUE) {
                break;
            }

            int temp = level[i].max - level[i].min + 1;
            if (temp > width) {
                width = temp;
                lv = i;
            }
        }

        System.out.println(lv + " " + width);
    }

    private static void inOrder(int node, int depth) {
        
        if (node == -1) {
            return;
        }
        
        inOrder(tree[node].left, depth + 1);

        level[depth].min = Math.min(level[depth].min, order);
        level[depth].max = Math.max(level[depth].max, order);
        order++;

        inOrder(tree[node].right, depth + 1);
    }

    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static class Level {
        int min, max;

        public Level(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
