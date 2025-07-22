import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] depth;
    static List<int[]>[] tree;
    static int[][] parent, minRoad, maxRoad;
    static int kmax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int temp = 1;
        while (temp <= n) {
            temp <<= 1;
            kmax++;
        }

        depth = new int[n + 1];
        tree = new List[n + 1];
        parent = new int[n + 1][kmax + 1];
        minRoad = new int[n + 1][kmax + 1];
        maxRoad = new int[n + 1][kmax + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new int[]{b, c});
            tree[b].add(new int[]{a, c});
        }

        dfs(1, 0, 0, 0);

        for (int k = 1; k <= kmax; k++) {
            for (int node = 1; node <= n; node++) {
                if (parent[node][k - 1] != 0) {
                    parent[node][k] = parent[parent[node][k - 1]][k - 1];
                    minRoad[node][k] = Math.min(minRoad[node][k - 1], minRoad[parent[node][k - 1]][k - 1]);
                    maxRoad[node][k] = Math.max(maxRoad[node][k - 1], maxRoad[parent[node][k - 1]][k - 1]);
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[] ans = solve(d, e);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }

        System.out.print(sb);
    }

    private static int[] solve(int a, int b) {
        if (depth[a] > depth[b]) { //b 노드의 깊이가 항상 더 깊도록
            int temp = a;
            a = b;
            b = temp;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int k = kmax; k >= 0; k--) {
            if ((1 << k) <= depth[b] - depth[a]) {
                min = Math.min(min, minRoad[b][k]);
                max = Math.max(max, maxRoad[b][k]);
                b = parent[b][k];
            }
        }

        for (int k = kmax; k >= 0; k--) {
            if (a == b) break;

            if (parent[b][k] != 0 && parent[a][k] != parent[b][k]) {
                min = Math.min(min, Math.min(minRoad[a][k], minRoad[b][k]));
                max = Math.max(max, Math.max(maxRoad[a][k], maxRoad[b][k]));
                a = parent[a][k];
                b = parent[b][k];
            }
        }

        if (a != b) {
            min = Math.min(min, Math.min(minRoad[a][0], minRoad[b][0]));
            max = Math.max(max, Math.max(maxRoad[a][0], maxRoad[b][0]));
        }
        
        return new int[]{min, max};
    }

    private static void dfs(int node, int par, int dep, int weight) {
        depth[node] = dep;
        parent[node][0] = par;
        minRoad[node][0] = weight;
        maxRoad[node][0] = weight;

        for (int[] child : tree[node]) {
            int childNode = child[0];

            if (childNode != par) {
                dfs(childNode, node, dep + 1, child[1]);
            }
        }
    }
}