import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char DOCUMENT = '$';

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int h, w;

    static char[][] map;
    static boolean[] isGetKey;
    static ArrayList<Point>[] lazy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];
            isGetKey = new boolean[26];
            lazy = new ArrayList[26];

            for (int i = 0; i < lazy.length; i++) {
                lazy[i] = new ArrayList<>();
            }

            for (char[] row : map) {
                Arrays.fill(row, EMPTY);
            }

            for (int i = 1; i <= h - 2; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 1; j <= w - 2; j++) {
                    map[i][j] = arr[j - 1];
                }
            }

            char[] keys = br.readLine().toCharArray();

            int index = 0;
            while (index < keys.length && keys[index] != '0') {
                isGetKey[keys[index++] - 'a'] = true;
            }

            sb.append(bfs()).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs() {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0));

        boolean[][] visit = new boolean[h][w];
        visit[0][0] = true;

        int count = 0;

        while (!qu.isEmpty()) {

            Point now = qu.poll();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w || visit[nx][ny] || map[nx][ny] == WALL) {
                    continue;
                }

                char ch = map[nx][ny];

                if (ch == DOCUMENT) {
                    count++;
                    map[nx][ny] = EMPTY;
                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny));
                } else if (Character.isUpperCase(ch)) {
                    if (isGetKey[ch - 'A']) {
                        map[nx][ny] = EMPTY;
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                    } else {
                        lazy[ch - 'A'].add(new Point(nx, ny));
                    }
                } else if (Character.isLowerCase(ch)) {
                    map[nx][ny] = EMPTY;
                    isGetKey[ch - 'a'] = true;

                    for (Point p : lazy[ch - 'a']) {
                        if (!visit[p.x][p.y]) {
                            visit[p.x][p.y] = true;
                            qu.offer(new Point(p.x, p.y));
                        }
                    }

                    lazy[ch - 'a'].clear();
                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny));
                } else {
                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny));
                }
            }
        }

        return count;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}