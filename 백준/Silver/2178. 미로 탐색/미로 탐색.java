import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{0, 0});

        boolean[][] visit = new boolean[n][m];
        visit[0][0] = true;

        while (!qu.isEmpty()) {

            int[] now = qu.poll();
            int x = now[0] / m;
            int y = now[0] % m;

            if (x == n - 1 && y == m - 1) {
                return now[1] + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny] || map[nx][ny] == '0') {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(new int[]{nx * m + ny, now[1] + 1});
            }
        }

        return -1;
    }
}