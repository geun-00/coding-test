import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Node>[] tree = new ArrayList[n];
            dp = new int[n];

            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());

                tree[u].add(new Node(v, d));
                tree[v].add(new Node(u, d));
            }
            
            sb.append(dfs(0, -1, tree)).append("\n");
        }

        System.out.print(sb);
    }

    private static int dfs(int node, int parent, List<Node>[] tree) {

        if (tree[node].size() == 1 && tree[node].get(0).node == parent) {
            return dp[node] = tree[node].get(0).dynamite;
        }

        for (Node child : tree[node]) {
            int nextNode = child.node;

            int cost = 0;
            if (nextNode != parent) {
                cost = dfs(nextNode, node, tree);
            }

            dp[node] += Math.min(cost, child.dynamite);
        }

        return dp[node];
    }

    static class Node {

        int node, dynamite;

        public Node(int node, int dynamite) {
            this.node = node;
            this.dynamite = dynamite;
        }
    }
}