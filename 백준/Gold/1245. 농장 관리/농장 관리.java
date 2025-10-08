import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
    static int n, m;
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    if (bfs(i, j)) {
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean bfs(int i, int j) {
        visited[i][j] = true;
        Queue<Integer> qu = new ArrayDeque<>();

        qu.offer(i);
        qu.offer(j);

        int height = map[i][j];
        boolean flag = true;

        while (!qu.isEmpty()) {
            Integer x = qu.poll();
            Integer y = qu.poll();

            for (int d = 0; d < dx.length; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (map[nx][ny] > height) {
                    flag = false;
                }

                if (map[nx][ny] == height && !visited[nx][ny]) {
                    qu.offer(nx);
                    qu.offer(ny);
                    visited[nx][ny] = true;
                }
            }
        }

        return flag;
    }
}