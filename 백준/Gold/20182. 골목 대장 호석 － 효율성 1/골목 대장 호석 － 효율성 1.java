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
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, value));
            graph[b].add(new Node(a, value));
        }

        for (int i = 1; i <= 20; i++) {
            if (dijkstra(graph, s, e, c, i, n)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    private static boolean dijkstra(List<Node>[] graph, int s, int e, int c, int limit, int n) {
        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        pq.offer(new Node(s, 0));

        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visit[cur.to]) continue;
            visit[cur.to] = true;

            for (Node next : graph[cur.to]) {
                int nextDist = dist[cur.to] + next.w;

                if (next.w > limit || nextDist > c) {
                    continue;
                }

                if (dist[next.to] > nextDist) {
                    dist[next.to] = nextDist;
                    pq.offer(new Node(next.to, nextDist));
                }
            }
        }

        return dist[e] != Integer.MAX_VALUE;
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}