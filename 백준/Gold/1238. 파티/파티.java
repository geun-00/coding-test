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

        int n = Integer.parseInt(st.nextToken()); //학생 수 = 노드 수
        int m = Integer.parseInt(st.nextToken()); //도로 수 = 에지 수
        int x = Integer.parseInt(st.nextToken()); //도착 지점

        ArrayList<Node>[] graph = new ArrayList[n + 1];

        int[][] dist = new int[n + 1][n + 1];
        boolean[][] visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, v));
        }

        Queue<Node> qu = new PriorityQueue<>();

        for (int start = 1; start <= n; start++) {

            qu.offer(new Node(start, 0));

            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (visit[start][now.adj]) {
                    continue;
                }
                visit[start][now.adj] = true;

                for (Node next : graph[now.adj]) {

                    if (dist[start][next.adj] > dist[start][now.adj] + next.v) {

                        dist[start][next.adj] = dist[start][now.adj] + next.v;

                        qu.offer(new Node(next.adj, dist[start][next.adj]));
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i][x] + dist[x][i]);
        }

        System.out.println(max);

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
