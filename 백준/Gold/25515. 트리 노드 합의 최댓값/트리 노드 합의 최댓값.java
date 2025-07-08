import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[p].add(c);
        }

        int[] value = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(graph, value, 0));

    }

    private static long dfs(List<Integer>[] graph, int[] value, int node) {
        long val = value[node];

        for (int child : graph[node]) {
            long childValue = dfs(graph, value, child);

            if (childValue > 0) {
                val += childValue;
            }
        }

        return val;
    }
}