import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] box;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int zero = 0;
    static ArrayList<Point> tomato = new ArrayList<>();
    static int min_day = Integer.MAX_VALUE;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());

                if (box[i][j] == 0) {
                    zero++;
                } else if (box[i][j] == 1) {
                    tomato.add(new Point(i, j));
                }
            }
        }

        if (zero == 0) {
            System.out.println(0);
        } else {
            bfs();
            System.out.println(min_day == Integer.MAX_VALUE ? -1 : min_day);
        }
    }

    private static void bfs() {
        Queue<Info> qu = new LinkedList<>();

        for (Point p : tomato) {
            qu.offer(new Info(p.x, p.y, 0));
            visit[p.x][p.y] = true;
        }

        int day = 0;
        while (!qu.isEmpty()) {
            Info now = qu.poll();
            int x = now.x;
            int y = now.y;
            day = now.day;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visit[nx][ny] && box[nx][ny] == 0) {
                        zero--;
                        visit[nx][ny] = true;
                        qu.offer(new Info(nx, ny, day + 1));
                    }
                }
            }
        }

        if (zero == 0) {
            min_day = day;
        }

    }

    static class Info{
        int x, y, day;

        public Info(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}
