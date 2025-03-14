import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        parent = new int[r][c];
        Point[] swans = new Point[2];
        Queue<Point> waterQu = new ArrayDeque<>();
        int index = 0;

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine()
                       .toCharArray();

            for (int j = 0; j < c; j++) {
                parent[i][j] = i * c + j;

                if (map[i][j] == 'L') {
                    swans[index++] = new Point(i, j);
                    waterQu.offer(new Point(i, j));
                } else if (map[i][j] == '.') {
                    waterQu.offer(new Point(i, j));
                }
            }
        }

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (map[x][y] == 'X') continue;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= r || ny >= c || map[nx][ny] == 'X') continue;

                    union(x, y, nx, ny);
                }
            }
        }

        int swan1 = find(swans[0].x, swans[0].y);
        int swan2 = find(swans[1].x, swans[1].y);

        int days = 0;
        while (swan1 != swan2) {
            days++;
            waterQu = meltAndUnion(waterQu);
            swan1 = find(swans[0].x, swans[0].y);
            swan2 = find(swans[1].x, swans[1].y);
        }

        System.out.println(days);
    }

    private static Queue<Point> meltAndUnion(Queue<Point> waterQu) {
        Queue<Point> temp = new ArrayDeque<>();

        while (!waterQu.isEmpty()) {
            Point p = waterQu.poll();
            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c || map[nx][ny] != 'X') continue;

                map[nx][ny] = '.';
                temp.offer(new Point(nx, ny));

                for (int d = 0; d < 4; d++) {
                    int nnx = nx + dx[d];
                    int nny = ny + dy[d];

                    if (nnx < 0 || nny < 0 || nnx >= r || nny >= c || map[nnx][nny] == 'X') continue;

                    union(nx, ny, nnx, nny);
                }
            }
        }

        return temp;
    }

    private static void union(int x1, int y1, int x2, int y2) {
        int num1 = find(x1, y1);
        int num2 = find(x2, y2);

        if (num1 != num2) {
            parent[num2 / c][num2 % c] = num1;
        }
    }

    private static int find(int x, int y) {
        if (parent[x][y] == (x * c + y)) {
            return parent[x][y];
        }
        return parent[x][y] = find(parent[x][y] / c, parent[x][y] % c);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}