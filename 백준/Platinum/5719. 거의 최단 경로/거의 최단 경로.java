import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static ArrayList<Integer>[] shortestPath;
    static boolean[][] blocked;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                System.out.print(sb);
                break;
            }

            graph = new ArrayList[n];
            shortestPath = new ArrayList[n];
            blocked = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                shortestPath[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph[u].add(new Node(v, p));
            }

            dijkstra(s, n);
            block(d, s);
            int[] dist = dijkstra(s, n);

            sb.append(dist[d] == INF ? -1 : dist[d]).append("\n");
        }
    }

    private static void block(int d, int s) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(d);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int prev : shortestPath[now]) {
                if (!blocked[prev][now]) {
                    blocked[prev][now] = true;
                    qu.offer(prev);
                }
            }
        }
    }

    private static int[] dijkstra(int s, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(s, 0));

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (dist[now.to] < now.w) {
                continue;
            }

            for (Node next : graph[now.to]) {

                if(blocked[now.to][next.to]) continue;

                if (dist[next.to] == dist[now.to] + next.w) {
                    shortestPath[next.to].add(now.to);
                }

                if (dist[next.to] > dist[now.to] + next.w) {
                    dist[next.to] = dist[now.to] + next.w;
                    qu.offer(new Node(next.to, dist[next.to]));

                    shortestPath[next.to].clear();
                    shortestPath[next.to].add(now.to);
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {

        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}