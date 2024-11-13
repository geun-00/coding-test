import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int w, h;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            visit = new boolean[h][w];
            map = new int[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            sb.append(count).append("\n");

        }

        System.out.print(sb);
    }

    private static void dfs(int x, int y) {

        visit[x][y] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x * w + y);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int i = 0; i < 8; i++) {

                int nx = now / w + dx[i];
                int ny = now % w + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w || visit[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(nx * w + ny);
            }
        }
    }
}