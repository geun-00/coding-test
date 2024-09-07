import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] weight;
    static boolean[] visit;
    static int[][] dp;

    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        weight = new int[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));

        visit = new boolean[n + 1];
        getResult(1, dp[1][1] > dp[1][0]);

        result.sort(null);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    private static void getResult(int node, boolean select) {
        visit[node] = true;

        if (select) {
            result.add(node);
        }

        for (int next : tree[node]) {
            if (!visit[next]) {

                if (select) {
                    getResult(next, false);
                } else {
                    getResult(next, dp[next][1] > dp[next][0]);
                }
            }
        }

    }

    private static void dfs(int node) {

        visit[node] = true;

        dp[node][0] = 0;
        dp[node][1] = weight[node];

        for (int next : tree[node]) {
            if (!visit[next]) {
                dfs(next);

                dp[node][0] += Math.max(dp[next][0], dp[next][1]);
                dp[node][1] += dp[next][0];
            }
        }
    }
}
