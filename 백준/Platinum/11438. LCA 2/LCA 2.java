import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static boolean[] visit;
    static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        k = 0;
        int temp = 1;

        while (temp < n) {
            temp <<= 1;
            k++;
        }

        depth = new int[n + 1];
        parent = new int[k][n + 1];

        visit = new boolean[n + 1];
        bfs();

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getLca(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    private static int getLca(int a, int b) {

        //무조건 b의 깊이를 깊은 노드로 맞추기
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        //깊이 맞추기
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

        visit[1] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(1);

        int size = 1;
        int level = 1;
        int count = 0;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int adj : tree[now]) {
                if (!visit[adj]) {

                    visit[adj] = true;
                    qu.offer(adj);

                    parent[0][adj] = now;
                    depth[adj] = level;
                }
            }

            count++;

            if (count == size) {
                count = 0;
                size = qu.size();
                level++;
            }
        }
    }
}