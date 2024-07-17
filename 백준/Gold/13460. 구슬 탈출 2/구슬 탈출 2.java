import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][][][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Point red = null;
        Point blue = null;

        map = new char[n][m];
        visit = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'R') {
                    red = new Point(i, j);
                } else if (map[i][j] == 'B') {
                    blue = new Point(i, j);
                }
            }
        }

        System.out.println(solve(red, blue));
    }

    private static int solve(Point r, Point b) {

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(r.x, r.y, b.x, b.y, 0));
        visit[r.x][r.y][b.x][b.y] = true;

        while (!qu.isEmpty()) {
            State now = qu.poll();

            if (now.count >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {

                int rx = now.rx;
                int ry = now.ry;
                int bx = now.bx;
                int by = now.by;

                while (map[rx + dx[i]][ry + dy[i]] != '#') {
                    rx += dx[i];
                    ry += dy[i];
                    if (map[rx][ry] == 'O') {
                        break;
                    }
                }

                while (map[bx + dx[i]][by + dy[i]] != '#') {
                    bx += dx[i];
                    by += dy[i];
                    if (map[bx][by] == 'O') {
                        break;
                    }
                }

                if (map[bx][by] == 'O') {
                    continue;
                }

                if (map[rx][ry] == 'O') {
                    return now.count + 1;
                }

                if (rx == bx && ry == by) {
                    if (map[rx][ry] != 'O') {
                        int moveRed = Math.abs(rx - now.rx) + Math.abs(ry - now.ry);
                        int moveBlue = Math.abs(bx - now.bx) + Math.abs(by - now.by);

                        if (moveRed > moveBlue) {
                            rx -= dx[i];
                            ry -= dy[i];
                        } else {
                            bx -= dx[i];
                            by -= dy[i];
                        }
                    }
                }

                if (!visit[rx][ry][bx][by]) {
                    visit[rx][ry][bx][by] = true;
                    qu.offer(new State(rx, ry, bx, by, now.count + 1));
                }
            }
        }

        return -1;
    }

    static class State {
        int rx, ry;
        int bx, by;
        int count;

        public State(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}
