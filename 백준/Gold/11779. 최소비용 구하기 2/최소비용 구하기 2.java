import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int[] path;
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        path = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, v));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, n);
        printResult(end);
    }

    private static void printResult(int end) {
        StringBuilder sb = new StringBuilder();

        sb.append(dist[end]).append("\n");

        ArrayList<Integer> route = new ArrayList<>();

        int now = end;
        while (now != 0) {
            route.add(now);
            now = path[now];
        }

        sb.append(route.size()).append("\n");

        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }

        System.out.print(sb);
    }

    private static void dijkstra(int start, int n) {
        boolean[] visit = new boolean[n + 1];
        dist[start] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

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

                    path[next.adj] = now.adj;
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
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
