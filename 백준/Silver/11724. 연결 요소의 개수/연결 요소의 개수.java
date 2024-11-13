import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visit = new boolean[n + 1];

        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                bfs(i, visit);
                count++;
            }
        }

        System.out.println(count);
    }

    private static void bfs(int node, boolean[] visit) {

        visit[node] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(node);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int adj : graph[now]) {
                if (!visit[adj]) {
                    visit[adj] = true;
                    qu.offer(adj);
                }
            }
        }
    }
}
