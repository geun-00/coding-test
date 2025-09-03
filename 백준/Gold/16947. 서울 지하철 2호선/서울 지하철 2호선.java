import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visit;
    static boolean[] isCycle;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        isCycle = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];

            dfs(i, i, 0);
        }

        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];

            ans[i] = bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.print(sb);
    }

    public static boolean dfs(int node, int start, int parent) {
        if (node == start && visit[node]) {
            isCycle[node] = true;
            return true;
        }

        if (visit[node]) {
            return false;
        }

        visit[node] = true;

        for (int next : graph[node]) {
            if (next != parent) {
                if (dfs(next, start, node)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int bfs(int target) {
        visit[target] = true;
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{target, 0});

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int node = cur[0];
            int depth = cur[1];

            if (isCycle[node]) {
                return depth;
            }

            for (int next : graph[node]) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(new int[]{next, depth + 1});
                }
            }
        }

        return 0;
    }
}