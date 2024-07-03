import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] tree;
    static boolean[] visit;
    static int max = 0;
    static int maxNode;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree[parent].add(new Node(child, w));
            tree[child].add(new Node(parent, w));
        }

        visit = new boolean[n + 1];
        dfs(1, 0);

        visit = new boolean[n + 1];
        dfs(maxNode, 0);

        System.out.println(max);
    }

    private static void dfs(int now, int dist) {
        if (dist > max) {
            max = dist;
            maxNode = now;
        }

        visit[now] = true;

        for (Node next : tree[now]) {
            if (!visit[next.adj]) {
                dfs(next.adj, dist + next.w);
            }
        }
    }

    static class Node {
        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }
    }
}
