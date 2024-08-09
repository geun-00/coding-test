import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int[][] dist;
    static int[][] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1][n + 1];
        result = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        for (int i = 1; i <= n; i++) {
            dijkstra(i, n);
        }

        printResult(n);
    }

    private static void printResult(int n) {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append(result[i][j]).append(" ");
                }

                if (j == n) {
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    private static void dijkstra(int s, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        boolean[] visit = new boolean[n + 1];

        qu.offer(new Node(s, 0));
        dist[s][s] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int nowNode = now.adj;

            if (visit[nowNode]) {
                continue;
            }
            visit[nowNode] = true;

            for (Node next : graph[nowNode]) {

                int nextNode = next.adj;
                int nextDist = next.w;

                if (dist[s][nextNode] > dist[s][nowNode] + nextDist) {
                    dist[s][nextNode] = dist[s][nowNode] + nextDist;

                    qu.offer(new Node(nextNode, dist[s][nextNode]));

                    //출발 노드와 직접 연결된 경우
                    if (nowNode == s) {
                        result[s][nextNode] = nextNode;
                    } else {
                        result[s][nextNode] = result[s][nowNode];
                    }
                }
            }
        }
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
