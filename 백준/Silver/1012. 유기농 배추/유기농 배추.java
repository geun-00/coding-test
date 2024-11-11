import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            visit = new boolean[n][m];
            map = new int[n][m];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                map[x][y] = 1;
            }

            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        count++;
                        bfs(i, j);
                    }
                }
            }
            
            System.out.println(count);
        }
    }

    private static void bfs(int x, int y) {
        visit[x][y] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x * m + y);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now / m + dx[i];
                int ny = now % m + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 0 || visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(nx * m + ny);
            }
        }
    }
}