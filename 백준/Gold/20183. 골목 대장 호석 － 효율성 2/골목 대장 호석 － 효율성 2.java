import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        long c = Long.parseLong(st.nextToken());

        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        long max = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());

            max = Math.max(max, w);

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        long left = 1, right = max;
        long ans = -1;

        while (left <= right) {
            long mid = (left + right) / 2;

            long[] dist = dijkstra(graph, mid, a, n, c);

            if (dist[b] <= c) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static long[] dijkstra(List<Node>[] graph, long limit, int start, int n, long c) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(node -> node.w));
        pq.offer(new Node(start, 0));
        
        boolean[] visit = new boolean[n];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            
            if (visit[node.to]) continue;
            visit[node.to] = true;

            for (Node next : graph[node.to]) {
                if (next.w > limit) continue;
                if (dist[node.to] + next.w > c) continue;

                if (dist[next.to] > dist[node.to] + next.w) {
                    dist[next.to] = dist[node.to] + next.w;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }

    static class Node {
        int to;
        long w;

        public Node(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }
}