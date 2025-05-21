import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 100_000;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n][n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            dist[a][b] = 1;
            dist[b][a] = 1;

            union(a, b);
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            map.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        int[] ans = new int[map.size()];
        int index = 0;

        for (List<Integer> group : map.values()) {
            int temp = INF;
            int leader = -1;

            for (int g1 : group) {
                int maxDist = 0;
                for (int g2 : group) {
                    maxDist = Math.max(maxDist, dist[g1][g2]);
                }

                if (maxDist < temp) {
                    temp = maxDist;
                    leader = g1;
                }
            }

            ans[index++] = leader + 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(index).append("\n");
        Arrays.sort(ans);
        for (int i : ans) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}