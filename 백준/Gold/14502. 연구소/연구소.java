import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> virus = new ArrayList<>();
    static ArrayList<Point> zero = new ArrayList<>();
    static int[] wall = new int[3];
    static int zero_count = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                } else if (map[i][j] == 0) {
                    zero.add(new Point(i, j));
                    zero_count++;
                }
            }
        }

        setWall(0, 0);

        System.out.println(max);
    }

    private static void setWall(int depth, int start) {
        if (depth == 3) {
            bfs(getNewMap());
            return;
        }

        for (int i = start; i < zero.size(); i++) {
            wall[depth] = i;
            setWall(depth + 1, i + 1);
        }
    }

    private static void bfs(int[][] map) {

        Queue<Point> qu = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];

        for (Point v : virus) {
            qu.offer(new Point(v.x, v.y));
            visit[v.x][v.y] = true;
        }

        int count = zero_count - 3;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visit[nx][ny] && map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        map[nx][ny] = 2;
                        qu.offer(new Point(nx, ny));
                        count--;
                    }
                }
            }
        }

        max = Math.max(max, count);
    }

    private static int[][] getNewMap() {
        int[][] new_map = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, new_map[i], 0, m);
        }

        for (int i = 0; i < 3; i++) {
            Point p = zero.get(wall[i]);
            new_map[p.x][p.y] = 1;
        }

        return new_map;
    }
}
