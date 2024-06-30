import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int max = 200_000 * 1000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] from1 = dijkstra(n, 1);
        int[] fromV1 = dijkstra(n, v1);
        int[] fromV2 = dijkstra(n, v2);

        int min1 = from1[v1] + fromV1[v2] + fromV2[n];
        int min2 = from1[v2] + fromV2[v1] + fromV1[n];

        if (min1 >= max && min2 >= max) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(min1, min2));
        }
    }

    private static int[] dijkstra(int n, int start) {
        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Arrays.fill(dist, max);

        dist[start] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.adj]) {
                continue;
            }
            visit[now.adj] = true;

            for (Node next : graph[now.adj]) {
                if (dist[next.adj] > dist[now.adj] + next.v) {
                    dist[next.adj] = dist[now.adj] + next.v;
                    qu.offer(new Node(next.adj, dist[next.adj]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int adj, v;

        public Node(int adj, int v) {
            this.adj = adj;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
}
