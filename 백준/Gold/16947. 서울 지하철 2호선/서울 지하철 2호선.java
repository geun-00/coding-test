import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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

        int[] in = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);

            in[u]++;
            in[v]++;
        }

        Queue<Integer> qu = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 1) {
                qu.offer(i);
            }
        }

        isCycle = new boolean[n + 1];
        Arrays.fill(isCycle, true);

        while (!qu.isEmpty()) {
            int cur = qu.poll();
            isCycle[cur] = false;

            for (int next : graph[cur]) {
                if (--in[next] == 1) {
                    qu.offer(next);
                }
            }
        }

        int[] ans = bfs(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.print(sb);
    }

    public static boolean dfs(int node, int parent) {
        visit[node] = true;

        for (int next : graph[node]) {
            if (!visit[next] && dfs(next, node)) {
                if (!isCycle[node]) {
                    isCycle[node] = true;
                    return true;
                }
            } else if (next != parent) {
                isCycle[node] = true;
                return true;
            }
        }

        return isCycle[node];
    }

    public static int[] bfs(int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> qu = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (isCycle[i]) {
                dist[i] = 0;
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int node = qu.poll();

            for (int next : graph[node]) {
                if (dist[next] == -1) {
                    dist[next] = dist[node] + 1;
                    qu.offer(next);
                }
            }
        }

        return dist;
    }
}