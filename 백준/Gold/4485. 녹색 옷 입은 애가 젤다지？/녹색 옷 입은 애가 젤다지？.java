import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = Integer.MAX_VALUE;
    static int[][] cave;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int num = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            cave = new int[n][n];
            dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = MAX;
                }
            }

            dijkstra();

            sb.append("Problem ").append(num++).append(": ").append(dist[n - 1][n - 1]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(0, 0, cave[0][0]));

        boolean[][] visit = new boolean[n][n];
        dist[0][0] = cave[0][0];

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            int x = now.x;
            int y = now.y;
            int d = now.d;

            if (visit[x][y]) {
                continue;
            }
            visit[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {

                    int newD = d + cave[nx][ny];

                    if (newD < dist[nx][ny]) {
                        dist[nx][ny] = newD;

                        qu.offer(new Node(nx, ny, newD));
                    }
                }
            }

        }
    }

    static class Node implements Comparable<Node> {
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
}
