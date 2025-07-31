import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, value});
            graph[b].add(new int[]{a, value});
        }

        solve(graph, s, 0, 0, e, c, new boolean[n + 1]);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void solve(List<int[]>[] graph, int node, int totalCost, int maxCost, int e, int c, boolean[] visit) {
        if (maxCost > ans || totalCost > c) {
            return;
        }

        if (node == e) {
            ans = maxCost;
            return;
        }

        for (int[] next : graph[node]) {
            if (!visit[next[0]]) {
                visit[next[0]] = true;
                solve(
                        graph,
                        next[0],
                        totalCost + next[1],
                        Math.max(next[1], maxCost),
                        e,
                        c,
                        visit
                );

                visit[next[0]] = false;
            }
        }
    }
}