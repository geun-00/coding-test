import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    static char[][] map = new char[12][6];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;

        while (true) {

            boolean check = false;

            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.') {

                        char color = map[i][j];
                        List<Point> targets = bfs(i, j, color);

                        if (targets.size() >= 4) {
                            check = true;
                            for (Point t : targets) {
                                map[t.x][t.y] = '.';
                            }
                        }
                    }
                }
            }

            if (check) {
                count++;
                moveDown();
            } else {
                break;
            }
        }

        System.out.println(count);
    }

    private static void moveDown() {
        Queue<Character> qu = new ArrayDeque<>();
        int temp;

        for (int i = 0; i < 6; i++) {
            for (int j = 11; j >= 0; j--) {
                if (map[j][i] != '.') {
                    qu.offer(map[j][i]);
                }
                map[j][i] = '.';
            }

            temp = 11;
            while (!qu.isEmpty()) {
                map[temp--][i] = qu.poll();
            }
        }
    }

    private static List<Point> bfs(int x, int y, char color) {

        List<Point> targets = new ArrayList<>();
        targets.add(new Point(x, y));

        boolean[][] visit = new boolean[12][6];
        visit[x][y] = true;

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 12 && ny < 6) {
                    if (!visit[nx][ny] && map[nx][ny] == color) {
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                        targets.add(new Point(nx, ny));
                    }
                }
            }
        }

        return targets;
    }
}
