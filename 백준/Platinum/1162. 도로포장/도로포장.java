import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        long[][] dist = dijkstra(n, k);

        long min = Long.MAX_VALUE;

        for (int i = 0; i <= k; i++) {
            min = Math.min(min, dist[n][i]);
        }

        System.out.println(min);
    }

    private static long[][] dijkstra(int n, int k) {

        long[][] dist = new long[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0, 0));

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            int count = now.count;
            int nowNode = now.adj;

            if (dist[nowNode][count] < now.dist) continue;

            for (Node next : graph[nowNode]) {

                int nextNode = next.adj;

                long nowDist = dist[nowNode][count];

                if (count < k && dist[nextNode][count + 1] > nowDist) {
                    dist[nextNode][count + 1] = nowDist;
                    qu.offer(new Node(nextNode, count + 1, dist[nextNode][count + 1]));
                }

                long newDist = nowDist + next.dist;

                if (dist[nextNode][count] > newDist) {
                    dist[nextNode][count] = newDist;
                    qu.offer(new Node(nextNode, count, newDist));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {

        int adj;
        int count;
        long dist;

        public Node(int adj, long dist) {
            this.adj = adj;
            this.dist = dist;
        }

        public Node(int adj, int count, long dist) {
            this.adj = adj;
            this.count = count;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}