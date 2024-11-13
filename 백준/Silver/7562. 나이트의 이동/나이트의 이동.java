import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            n = Integer.parseInt(br.readLine());

            visit = new boolean[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            if (sx == ex && sy == ey) {
                sb.append(0).append("\n");
                continue;
            }

            sb.append(bfs(sx, sy, ex, ey)).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int sx, int sy, int ex, int ey) {

        visit[sx][sy] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(sx * n + sy);

        int count = 0;

        while (!qu.isEmpty()) {

            count++;

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                int now = qu.poll();

                for (int d = 0; d < 8; d++) {

                    int nx = now / n + dx[d];
                    int ny = now % n + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny]) {
                        continue;
                    }

                    if (nx == ex && ny == ey) {
                        return count;
                    }

                    visit[nx][ny] = true;
                    qu.offer(nx * n + ny);
                }
            }
        }

        return -1;
    }
}