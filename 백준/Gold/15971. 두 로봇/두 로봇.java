import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<int[]>[] graph;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        graph = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        dfs(r1, r2, 0, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int r1, int r2, int w, int sum, int max) {
        visited[r1] = true;
        max = Math.max(max, w);

        if (r1 == r2) {
            ans = sum - max;
            return;
        }

        for (int[] next : graph[r1]) {
            if (!visited[next[0]]) {
                dfs(next[0], r2, next[1], sum + next[1], max);
            }
        }
    }
}