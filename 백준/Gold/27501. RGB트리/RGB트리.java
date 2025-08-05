import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    static List<Integer>[] graph;
    static int[][] rgb;
    static int[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][3];
        graph = new List[n + 1];
        rgb = new int[n + 1][3];
        arr = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            rgb[i + 1][0] = r;
            rgb[i + 1][1] = g;
            rgb[i + 1][2] = b;
        }

        int root = 1;
        visit = new boolean[n + 1];

        solve(root);
        System.out.println(Math.max(dp[root][0], Math.max(dp[root][1], dp[root][2])));

        int color = 0;
        if (dp[root][color] < dp[root][1]) color = 1;
        if (dp[root][color] < dp[root][2]) color = 2;

        visit = new boolean[n + 1];
        solve(root, color);

        char[] temp = {'R', 'G', 'B'};
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(temp[arr[i]]);
        }
        System.out.print(sb);
    }

    private static void solve(int node, int color) {
        arr[node] = color;
        visit[node] = true;

        for (int child : graph[node]) {
            if (!visit[child]) {
                int max = Integer.MIN_VALUE;
                int selectedColor = -1;

                for (int i = 0; i < 3; i++) {
                    if (i == color) continue;

                    if (dp[child][i] > max) {
                        max = dp[child][i];
                        selectedColor = i;
                    }
                }

                solve(child, selectedColor);
            }
        }
    }

    private static void solve(int node) {
        visit[node] = true;

        dp[node][0] = rgb[node][0];
        dp[node][1] = rgb[node][1];
        dp[node][2] = rgb[node][2];

        for (int child : graph[node]) {
            if (!visit[child]) {
                solve(child);

                dp[node][0] += Math.max(dp[child][1], dp[child][2]);
                dp[node][1] += Math.max(dp[child][0], dp[child][2]);
                dp[node][2] += Math.max(dp[child][0], dp[child][1]);
            }
        }
    }
}
