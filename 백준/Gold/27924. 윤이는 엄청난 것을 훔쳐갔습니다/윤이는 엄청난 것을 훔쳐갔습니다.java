import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            tree[u].add(v);
            tree[v].add(u);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];

        dfs(-1, a, A, 0);
        dfs(-1, b, B, 0);
        dfs(-1, c, C, 0);

        String ans = "NO";
        for (int i = 0; i < n; i++) {
            boolean isLeafNode = tree[i].size() == 1;

            if (isLeafNode && A[i] < B[i] && A[i] < C[i]) {
                ans = "YES";
                break;
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int parent, int node, int[] dist, int depth) {
        dist[node] = depth;

        for (int next : tree[node]) {
            if (next != parent) {
                dfs(node, next, dist, depth + 1);
            }
        }
    }
}