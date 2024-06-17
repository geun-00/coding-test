import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); //정점 개수
        int E = Integer.parseInt(st.nextToken()); //간선 개수

        int k = Integer.parseInt(br.readLine()); //시작 정점

        ArrayList<Node>[] graph = new ArrayList[V + 1];
        boolean[] visit = new boolean[V + 1];
        int[] dist = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {

            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(k, 0));

        dist[k] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.adj]) {
                continue;
            }
            visit[now.adj] = true;

            for (Node next : graph[now.adj]) {

                if (dist[next.adj] > dist[now.adj] + next.w) {

                    dist[next.adj] = dist[now.adj] + next.w;
                    qu.offer(new Node(next.adj, dist[next.adj]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < V + 1; i++) {
            sb.append(visit[i] ? dist[i] : "INF").append("\n");
        }

        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {

        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
