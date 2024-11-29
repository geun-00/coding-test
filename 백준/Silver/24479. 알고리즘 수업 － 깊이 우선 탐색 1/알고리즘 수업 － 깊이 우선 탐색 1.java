import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static int[] ans;
    static boolean[] visit;
    static int order = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        ans = new int[n];
        graph = new ArrayList[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 0; i < n; i++) {
            graph[i].sort(null);
        }

        dfs(r - 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(ans[i]).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int node) {

        visit[node] = true;
        ans[node] = order++;

        for (int next : graph[node]) {
            if (!visit[next]) {
                dfs(next);
            }
        }
    }

}