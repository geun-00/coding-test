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

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
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

        int[] fromMinjun = dijkstra(1, v);
        int[] fromGunwoo = dijkstra(p, v);

        int d = fromMinjun[v] - (fromGunwoo[1] + fromGunwoo[v]);

        System.out.println(d == 0 ? "SAVE HIM" : "GOOD BYE");
    }

    private static int[] dijkstra(int start, int v) {

        boolean[] visit = new boolean[v + 1];

        int[] dist = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;

            for (Node next : graph[now.to]) {

                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;

                    qu.offer(new Node(next.to, dist[next.to]));
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