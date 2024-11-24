import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Pair>[] tree;
    static int[] depth;
    static int[][] parent;
    static int[] dist;
    static boolean[] visit;

    static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        k = 1;

        while ((1 << k) < n) {
            k++;
        }

        tree = new ArrayList[n];
        depth = new int[n];
        parent = new int[k][n];
        dist = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Pair(b, c));
            tree[b].add(new Pair(a, c));
        }

        bfs();

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int lca = getLCA(a, b);

            sb.append(dist[a] + dist[b] - (2 * dist[lca])).append("\n");
        }

        System.out.print(sb);
    }

    private static int getLCA(int a, int b) {

        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = k - 1; i >= 0; i--) {
            if ((1 << i) <= depth[b] - depth[a]) {
                b = parent[i][b];
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            if (a == b) {
                break;
            }
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return a != b ? parent[0][a] : a;
    }

    private static void bfs() {

        ArrayDeque<Integer> qu = new ArrayDeque<>();
        qu.offer(0);

        visit[0] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (Pair p : tree[now]) {

                int next = p.idx;

                if (!visit[next]) {

                    visit[next] = true;
                    qu.offer(next);

                    parent[0][next] = now;
                    depth[next] = depth[now] + 1;
                    dist[next] = dist[now] + p.val;
                }
            }
        }
    }

    static class Pair {

        int idx, val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}