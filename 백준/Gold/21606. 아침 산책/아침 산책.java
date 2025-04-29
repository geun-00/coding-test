import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static char[] arr;
    static List<Integer>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            tree[u].add(v);
            tree[v].add(u);

            if (arr[u] == '1' && arr[v] == '1') {
                ans += 2;
            }
        }

        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (arr[i] == '0' && !visit[i]) {
                int count = dfs(i);
                ans += count * (count - 1);
            }
        }

        System.out.println(ans);
    }

    private static int dfs(int node) {
        visit[node] = true;
        int count = 0;

        for (int next : tree[node]) {
            if (arr[next] == '1') {
                count++;
            } else if (!visit[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}