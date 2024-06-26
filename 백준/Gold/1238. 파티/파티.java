import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //학생 수 = 노드 수
        int m = Integer.parseInt(st.nextToken()); //도로 수 = 에지 수
        int x = Integer.parseInt(st.nextToken()); //도착 지점

        ArrayList<Node>[] graph = new ArrayList[n + 1];
        ArrayList<Node>[] reversedGraph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reversedGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, v));
            reversedGraph[e].add(new Node(s, v));
        }

        int[] distToX = dijkstra(n, reversedGraph, x);
        int[] distFromX = dijkstra(n, graph, x);

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (i != x) {
                max = Math.max(max, distToX[i] + distFromX[i]);
            }
        }

        System.out.println(max);
    }

    private static int[] dijkstra(int n, ArrayList<Node>[] graph, int start) {

        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        int[] dist = new int[n + 1];
        boolean[] visit = new boolean[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

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
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
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
