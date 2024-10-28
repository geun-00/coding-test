import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        long[] dist = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v].add(new Node(u, c));
        }

        PriorityQueue<Node> qu = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            int interview = Integer.parseInt(st.nextToken());
            qu.offer(new Node(interview, 0));
            dist[interview] = 0;
        }

        boolean[] visit = new boolean[n + 1];

        while (!qu.isEmpty()) {
            Node now = qu.poll();
            if (visit[now.to]) continue;
            visit[now.to] = true;

            for (Node next : graph[now.to]) {
                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;
                    qu.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        int city = 0;
        long maxDist = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] > maxDist && dist[i] != Long.MAX_VALUE) {
                city = i;
                maxDist = dist[i];
            }
        }

        System.out.println(city);
        System.out.println(maxDist);

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
