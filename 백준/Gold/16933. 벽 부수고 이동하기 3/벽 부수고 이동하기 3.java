import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[][][] visit = new boolean[n][m][k + 1];
        visit[0][0][0] = true;

        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(0, 0, 0, 1));

        while (!qu.isEmpty()) {
            Node cur = qu.poll();
            int x = cur.x;
            int y = cur.y;
            int wall = cur.wall;
            int dist = cur.dist;
            boolean isAM = (dist % 2 == 1);

            if (x == n - 1 && y == m - 1) {
                System.out.println(dist);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == '0') {
                    if (!visit[nx][ny][wall]) {
                        visit[nx][ny][wall] = true;
                        qu.offer(new Node(nx, ny, wall, dist + 1));
                    }
                }
                else {
                    if (wall + 1 <= k) {
                        if (isAM) {
                            if (!visit[nx][ny][wall + 1]) {
                                visit[nx][ny][wall + 1] = true;
                                qu.offer(new Node(nx, ny, wall + 1, dist + 1));
                            }
                        } else {
                            qu.offer(new Node(x, y, wall, dist + 1));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static class Node {
        int x, y, wall, dist;

        public Node(int x, int y, int wall, int dist) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.dist = dist;
        }
    }
}
