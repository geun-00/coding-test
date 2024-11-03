import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int n, k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        long left = 0, right = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));

            right = Math.max(right, c);
        }

        long ans = -1;

        while (left <= right) {

            long mid = (left + right) / 2;

            if (dijkstra(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean dijkstra(long val) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        boolean[] visit = new boolean[n + 1];

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        int count = 0;

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (dist[now.to] < now.weight) continue;

            for (Node next : graph[now.to]) {

                long weight = now.weight;

                if (next.weight > val) {
                    weight++;
                }

                if (weight < dist[next.to]) {
                    dist[next.to] = weight;
                    qu.offer(new Node(next.to, weight));
                }
            }
        }

        return dist[n] <= k;
    }

    static class Node implements Comparable<Node> {
        int to;
        long weight;

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
