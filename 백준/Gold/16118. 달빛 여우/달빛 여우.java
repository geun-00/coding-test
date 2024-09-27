import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, d * 2L));
            graph[b].add(new Node(a, d * 2L));
        }

        long[] foxDist = foxDijkstra();


        long[][] wolfDist = wolfDijkstra();
 

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (foxDist[i] < Math.min(wolfDist[i][0], wolfDist[i][1])) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static long[][] wolfDijkstra() {
        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0, 0));

        boolean[][] visit = new boolean[n + 1][2];

        long[][] dist = new long[n + 1][2];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int nowTo = now.to;
            int state = now.state;

            if (visit[nowTo][state]) {
                continue;
            }
            visit[nowTo][state] = true;

            for (Node next : graph[nowTo]) {
                if (state == 0) {
                    long newDist = dist[nowTo][state] + next.weight / 2;
                    if (dist[next.to][1] > newDist) {
                        dist[next.to][1] = newDist;
                        qu.offer(new Node(next.to, newDist, 1));
                    }
                } else {
                    long newDist = dist[nowTo][state] + next.weight * 2;
                    if (dist[next.to][0] > newDist) {
                        dist[next.to][0] = newDist;
                        qu.offer(new Node(next.to, newDist, 0));
                    }
                }
            }
        }

        return dist;
    }

    private static long[] foxDijkstra() {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        boolean[] visit = new boolean[n + 1];

        long[] dist = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[1] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int nowTo = now.to;
            if (visit[nowTo]) {
                continue;
            }
            visit[nowTo] = true;

            for (Node next : graph[nowTo]) {

                long newDist = dist[nowTo] + next.weight;
                int nextTo = next.to;

                if (dist[nextTo] > newDist) {
                    dist[nextTo] = newDist;
                    qu.offer(new Node(nextTo, dist[nextTo]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int to;
        long weight;
        int state;

        public Node(int to, long weight, int state) {
            this.to = to;
            this.weight = weight;
            this.state = state;
        }

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }
}
