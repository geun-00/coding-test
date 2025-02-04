import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static char[][] grid;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[m][n];
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        int white = 0;
        int blue = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    int size = bfs(i, j, grid[i][j]);

                    if (grid[i][j] == 'W') {
                        white += size;
                    } else {
                        blue += size;
                    }
                }
            }
        }

        System.out.println(white + " " + blue);
    }

    private static int bfs(int sx, int sy, char team) {

        int size = 1;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(sx);
        qu.offer(sy);

        visit[sx][sy] = true;

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                if (visit[nx][ny] || grid[nx][ny] != team) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(nx);
                qu.offer(ny);
                size++;
            }
        }

        return size * size;
    }
}