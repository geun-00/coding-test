import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] tree;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        tree = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        int l = Integer.parseInt(br.readLine());
        value = new int[n + 1];
        Arrays.fill(value, -1);

        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            value[k] = t;
        }

        solve(r, true, -1);

        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            sb.append(value[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(int node, boolean isMax, int parent) {
        if (value[node] != -1) {
            return;
        }
        int result = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int child : tree[node]) {
            if (child != parent) {
                solve(child, !isMax, node);

                if (isMax) {
                    result = Math.max(result, value[child]);
                } else {
                    result = Math.min(result, value[child]);
                }
            }
        }

        value[node] = result;
    }
}