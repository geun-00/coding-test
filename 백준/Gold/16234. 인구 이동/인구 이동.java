import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] land;
    static int n, l, r;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static ArrayList<Point> list;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        land = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        while (true) {

            visit = new boolean[n][n];
            boolean check = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {

                        list = new ArrayList<>();
                        int sum = bfs(i, j);

                        if (!list.isEmpty()) {
                            for (Point p : list) {
                                int temp = sum / list.size();
                                land[p.x][p.y] = temp;
                            }
                            check = true;
                        }
                    }
                }
            }
            if (!check) {
                break;
            }

            days++;
        }

        System.out.println(days);
    }

    private static int bfs(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();

        qu.offer(new Point(x, y));

        int sum = 0;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                    int diff = Math.abs(land[now.x][now.y] - land[nx][ny]);

                    if (l <= diff && diff <= r) {
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                        list.add(new Point(nx, ny));
                        sum += land[nx][ny];
                    }
                }
            }
        }

        return sum;
    }
}
