import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int w, h;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> fires;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            fires = new ArrayList<>();

            map = new char[h][w];

            int x = 0, y = 0;

            for (int i = 0; i < h; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    map[i][j] = arr[j];

                    if (map[i][j] == '*') {
                        fires.add(new Point(i, j));
                    }
                    if (map[i][j] == '@') {
                        x = i;
                        y = j;
                    }
                }
            }

            int res = bfs(x, y);

            sb.append(res != -1 ? res : "IMPOSSIBLE").append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int x, int y) {

        Queue<Point> fire = new ArrayDeque<>();
        for (Point p : fires) {
            fire.offer(new Point(p.x, p.y));
        }

        Queue<SangGeun> sang = new ArrayDeque<>();
        sang.offer(new SangGeun(x, y, 1));

        boolean[][] visit = new boolean[h][w];
        visit[x][y] = true;

        while (!sang.isEmpty()) {

            int f_size = fire.size();
            for (int i = 0; i < f_size; i++) {
                Point f = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = f.x + dx[d];
                    int ny = f.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w || map[nx][ny] != '.') {
                        continue;
                    }

                    map[nx][ny] = '*';
                    fire.offer(new Point(nx, ny));
                }
            }

            int s_size = sang.size();

            for (int i = 0; i < s_size; i++) {
                SangGeun s = sang.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = s.x + dx[d];
                    int ny = s.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        return s.count;
                    }

                    if (map[nx][ny] != '.' || visit[nx][ny]) {
                        continue;
                    }

                    visit[nx][ny] = true;
                    sang.offer(new SangGeun(nx, ny, s.count + 1));
                }
            }
        }

        return -1;
    }

    static class SangGeun {
        int x, y, count;

        public SangGeun(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
