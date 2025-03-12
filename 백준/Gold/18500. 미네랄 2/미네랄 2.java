import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static char[][] cave;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cave = new char[r][c];

        for (int i = 0; i < r; i++) {
            cave[i] = br.readLine()
                        .toCharArray();
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            solve(r - height, (i % 2 == 0));
        }

        StringBuilder sb = new StringBuilder();
        for (char[] arr : cave) {
            sb.append(arr)
              .append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(int height, boolean isLeft) {
        if (isLeft) {
            for (int i = 0; i < c; i++) {
                if (cave[height][i] == 'x') {
                    cave[height][i] = '.';
                    break;
                }
            }
        } else {
            for (int i = c - 1; i >= 0; i--) {
                if (cave[height][i] == 'x') {
                    cave[height][i] = '.';
                    break;
                }
            }
        }

        moveDown();
    }

    private static void moveDown() {

        List<Point> target = new ArrayList<>();
        boolean[][] visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visit[i][j] && cave[i][j] == 'x') {
                    List<Point> result = bfs(i, j, visit);
                    if (!result.isEmpty()) {
                        target = result;
                        break;
                    }
                }
            }
        }

        int minDrop = Integer.MAX_VALUE;
        boolean[][] isMineral = new boolean[r][c];

        for (Point p : target) {
            isMineral[p.x][p.y] = true;
        }

        for (Point p : target) {
            int drop = 0;
            for (int i = p.x + 1; i < r; i++) {
                if (cave[i][p.y] == 'x' && !isMineral[i][p.y]) break;
                drop++;
            }
            minDrop = Math.min(minDrop, drop);
        }

        for (Point p : target) {
            cave[p.x][p.y] = '.';
        }
        for (Point p : target) {
            cave[p.x + minDrop][p.y] = 'x';
        }

    }

    private static List<Point> bfs(int i, int j, boolean[][] visit) {
        List<Point> target = new ArrayList<>();
        target.add(new Point(i, j));

        visit[i][j] = true;
        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(i);
        qu.offer(j);

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if (visit[nx][ny] || cave[nx][ny] == '.') continue;

                visit[nx][ny] = true;
                target.add(new Point(nx, ny));
                qu.offer(nx);
                qu.offer(ny);
            }
        }

        target.sort((a, b) -> b.x - a.x);

        if (target.get(0).x < r - 1) {
            return target;
        }
        return new ArrayList<>();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}