import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            graph[i].sort(null);
        }

        boolean[] visit = new boolean[n + 1];
        dfs(v, visit);

        sb.append("\n");

        bfs(v, n);

        System.out.print(sb);
    }

    private static void bfs(int v, int n) {

        boolean[] visit = new boolean[n + 1];
        visit[v] = true;

        sb.append(v).append(" ");

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(v);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int adj : graph[now]) {
                if (!visit[adj]) {
                    visit[adj] = true;
                    qu.offer(adj);
                    sb.append(adj).append(" ");
                }
            }
        }
    }

    private static void dfs(int node, boolean[] visit) {

        sb.append(node).append(" ");

        visit[node] = true;

        for (int adj : graph[node]) {
            if (!visit[adj]) {
                dfs(adj, visit);
            }
        }
    }
}