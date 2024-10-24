import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] path;
    static ArrayList<Node>[] graph;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        path = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }

        int[] fromStart = dijkstra(-1);

        if (fromStart[n] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        int prev = path[n];
        boolean[] arr = new boolean[n];
        while (prev != 1) {
            arr[prev] = true;
            prev = path[prev];
        }

        int init = fromStart[n];

        int result = 0;
        for (int i = 2; i < n; i++) {
            if (arr[i]) {
                int[] dist = dijkstra(i);
                if (dist[n] == Integer.MAX_VALUE) {
                    result = -1;
                    break;
                }
                result = Math.max(result, dist[n] - init);
            }
        }

        System.out.println(result);

    }

    private static int[] dijkstra(int ban) {

        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        boolean[] visit = new boolean[n + 1];

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;

            for (Node next : graph[now.to]) {
                if (next.to == ban) {
                    continue;
                }
                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;
                    qu.offer(new Node(next.to, dist[next.to]));
                    path[next.to] = now.to;
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {

        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
