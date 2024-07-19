import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dist;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        dist = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], 100 * 100 * 15 + 1);
            dist[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            //다익스트라
            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }

        dijkstra(n);

        printResult(n, m, items);
    }

    private static void printResult(int n, int m, int[] items) {
        int max = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

    private static void dijkstra(int n) {

        boolean[][] visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            PriorityQueue<Node> qu = new PriorityQueue<>();
            qu.offer(new Node(i, 0));

            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (visit[i][now.adj]) {
                    continue;
                }
                visit[i][now.adj] = true;

                for (Node next : graph[now.adj]) {
                    if (dist[i][next.adj] > dist[i][now.adj] + next.w) {
                        dist[i][next.adj] = dist[i][now.adj] + next.w;
                        qu.offer(new Node(next.adj, dist[i][next.adj]));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}