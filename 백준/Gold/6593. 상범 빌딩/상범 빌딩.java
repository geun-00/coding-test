import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][][] visit;
    static char[][][] arr;
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};
    static int L, R, C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            arr = new char[L][R][C];
            visit = new boolean[L][R][C];

            Point start = null;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    arr[i][j] = br.readLine().toCharArray();
                    for (int k = 0; k < C; k++) {
                        if (arr[i][j][k] == 'S') {
                            start = new Point(i, j, k);
                        }
                    }
                }
                br.readLine();
            }

            int x = bfs(start);

            if (x != -1) {
                sb.append("Escaped in ").append(x).append(" minute(s).");
            } else {
                sb.append("Trapped!");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(Point start) {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(start);

        visit[start.z][start.x][start.y] = true;

        int time = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                Point now = qu.poll();

                int z = now.z;
                int x = now.x;
                int y = now.y;

                if (arr[z][x][y] == 'E') return time;

                for (int d = 0; d < 6; d++) {

                    int nz = z + dz[d];
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nz < 0 || nx < 0 || ny < 0 || nz >= L || nx >= R || ny >= C) continue;
                    if (visit[nz][nx][ny] || arr[nz][nx][ny] == '#') continue;

                    visit[nz][nx][ny] = true;
                    qu.offer(new Point(nz, nx, ny));
                }
            }

            time++;
        }

        return -1;
    }

    static class Point {
        int z, x, y;

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}