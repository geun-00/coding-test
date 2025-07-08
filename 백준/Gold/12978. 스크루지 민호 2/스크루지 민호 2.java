import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dp = new int[n + 1][2];

        solve(graph, 1, 0);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void solve(List<Integer>[] graph, int node, int parent) {

        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int child : graph[node]) {
            if (child != parent) {

                solve(graph, child, node);

                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
                dp[node][0] += dp[child][1];
            }
        }
    }
}