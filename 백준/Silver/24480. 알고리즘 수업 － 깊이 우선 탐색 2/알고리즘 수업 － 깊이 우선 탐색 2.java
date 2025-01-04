import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int order = 1;
    static int[] ans;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()) - 1;

        ans = new int[n];
        visit = new boolean[n];
        List<Integer>[] graph = new ArrayList[n];

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
            graph[i].sort(Comparator.reverseOrder());
        }

        dfs(r, graph);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans)
            .forEach(o -> sb.append(o).append("\n"));

        System.out.print(sb);
    }

    private static void dfs(int node, List<Integer>[] graph) {

        visit[node] = true;

        ans[node] = order++;

        for (int child : graph[node]) {
            if (!visit[child]) {
                dfs(child, graph);
            }
        }
    }
}