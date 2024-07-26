import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int r, c;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Point[] airCleaner = new Point[2];
    static Queue<Dust> dusts = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        boolean first = true;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (first) {
                        airCleaner[0] = new Point(i, j);
                        first = false;
                    } else {
                        airCleaner[1] = new Point(i, j);
                    }
                }
            }
        }

        while (t-- > 0) {

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] >= 5) {
                        dusts.offer(new Dust(i, j, map[i][j]));
                    }
                }
            }

            spread();
            clean();
        }

        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    private static void spread() {

        while (!dusts.isEmpty()) {

            Dust dust = dusts.poll();

            int x = dust.x;
            int y = dust.y;

            int spreadAmount = dust.amount / 5;
            int count = 0;

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] >= 0) {
                    map[nx][ny] += spreadAmount;
                    count++;
                }
            }

            map[x][y] -= spreadAmount * count;
        }
    }

    private static void clean() {

        // 위쪽 공기청정기 반시계방향 순환
        cleanUp();

        // 아래쪽 공기청정기 시계방향 순환
        cleanDown();
    }

    private static void cleanUp() {
        
        int x = airCleaner[0].x;

        // ↓
        for (int i = x - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // <-
        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        // ↑
        for (int i = 0; i < x; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        // ->
        for (int i = c - 1; i > 1; i--) {
            map[x][i] = map[x][i - 1];
        }

        map[x][1] = 0;
    }

    private static void cleanDown() {

        int x = airCleaner[1].x;

        // ↑
        for (int i = x + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // <-
        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }
        // ↓
        for (int i = r - 1; i > x; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }
        // ->
        for (int i = c - 1; i > 1; i--) {
            map[x][i] = map[x][i - 1];
        }

        map[x][1] = 0;
    }

    static class Dust {
        int x, y, amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }
}
