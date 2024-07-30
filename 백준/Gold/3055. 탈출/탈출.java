import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int r, c;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        Point S = null;
        Queue<Point> waters = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = arr[j];

                if (map[i][j] == 'S') {
                    S = new Point(i, j);
                } else if (map[i][j] == '*') {
                    waters.offer(new Point(i, j));
                }
            }
        }

        System.out.println(bfs(S, waters));
    }

    private static String bfs(Point s, Queue<Point> waters) {

        Queue<Hedgehog> qu = new ArrayDeque<>();
        qu.offer(new Hedgehog(s.x, s.y, 0));

        while (!qu.isEmpty()) {

            int size = waters.size();
            for (int i = 0; i < size; i++) {
                Point now = waters.poll();

                int x = now.x;
                int y = now.y;

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        waters.offer(new Point(nx, ny));
                    }
                }
            }

            size = qu.size();
            for (int i = 0; i < size; i++) {
                Hedgehog now = qu.poll();

                int x = now.x;
                int y = now.y;

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                        if (map[nx][ny] == 'D') {
                            return String.valueOf(now.move + 1);
                        }
                        if (map[nx][ny] == '.') {
                            map[nx][ny] = 'S';
                            qu.offer(new Hedgehog(nx, ny, now.move + 1));
                        }

                    }
                }
            }
        }

        return "KAKTUS";
    }

    static class Hedgehog {
        int x, y, move;

        public Hedgehog(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
