import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][c];
        boolean[][] fireVisit = new boolean[r][c];
        boolean[][] jihunVisit = new boolean[r][c];

        Queue<Point> fires = new ArrayDeque<>();
        Queue<Jihun> jihun = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = arr[j];
                if (map[i][j] == 'J') {
                    jihun.offer(new Jihun(i, j, 0));
                    jihunVisit[i][j] = true;
                } else if (map[i][j] == 'F') {
                    fires.offer(new Point(i, j));
                    fireVisit[i][j] = true;
                }
            }
        }

        while (!jihun.isEmpty()) {

            int fireSize = fires.size();
            for (int i = 0; i < fireSize; i++) {
                Point f = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = f.x + dx[d];
                    int ny = f.y + dy[d];

                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] == '.' && !fireVisit[nx][ny]) {
                        fireVisit[nx][ny] = true;
                        map[nx][ny] = 'F';
                        fires.offer(new Point(nx, ny));
                    }
                }
            }

            int jihunSize = jihun.size();
            for (int i = 0; i < jihunSize; i++) {
                Jihun j = jihun.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = j.x + dx[d];
                    int ny = j.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                        System.out.println(j.count + 1);
                        return;
                    }

                    if (map[nx][ny] == '.' && !jihunVisit[nx][ny]) {
                        jihunVisit[nx][ny] = true;
                        jihun.offer(new Jihun(nx, ny, j.count + 1));
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");

    }

    static class Jihun {

        int x, y, count;

        public Jihun(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
