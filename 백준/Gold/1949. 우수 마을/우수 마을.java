import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] nums;
    static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        nums = new int[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {

        visit[node] = true;

        dp[node][0] = 0;
        dp[node][1] = nums[node];

        for (int to : tree[node]) {
            if (!visit[to]) {
                dfs(to);

                dp[node][0] += Math.max(dp[to][0], dp[to][1]);
                dp[node][1] += dp[to][0];
            }
        }
    }
}