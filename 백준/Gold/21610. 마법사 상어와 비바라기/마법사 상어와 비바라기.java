import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> cloud = new ArrayDeque<>();
        cloud.offer(new Point(n - 1, 0));
        cloud.offer(new Point(n - 1, 1));
        cloud.offer(new Point(n - 2, 0));
        cloud.offer(new Point(n - 2, 1));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            boolean[][] visit = new boolean[n][n];

            int size = cloud.size();
            for (int j = 0; j < size; j++) {
                Point c = cloud.poll();
                int nx = (c.x + dx[d] * (s % n) + n) % n;
                int ny = (c.y + dy[d] * (s % n) + n) % n;

                visit[nx][ny] = true;
                cloud.offer(new Point(nx, ny));
            }

            for (Point c : cloud) {
                map[c.x][c.y] += 1;
            }

            while (!cloud.isEmpty()) {
                Point c = cloud.poll();
                int count = 0;
                for (int j = 1; j < 8; j += 2) {
                    int nx = c.x + dx[j];
                    int ny = c.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                        continue;
                    }
                    if (map[nx][ny] > 0) count++;
                }
                map[c.x][c.y] += count;
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!visit[j][k] && map[j][k] >= 2) {
                        map[j][k] -= 2;
                        cloud.offer(new Point(j, k));
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }
}
