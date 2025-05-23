package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3197">백준 3197번 - 백조의 호수</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3197%EB%B2%88-%EB%B0%B1%EC%A1%B0%EC%9D%98-%ED%98%B8%EC%88%98">velog</a>
 * @since 2025-03-14
 */
public class BJ_3197 {

    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Point> waterQu, nextWaterQu;
    static Queue<Point> swanQu, nextSwanQu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        waterQu = new ArrayDeque<>();
        nextWaterQu = new ArrayDeque<>();
        swanQu = new ArrayDeque<>();
        nextSwanQu = new ArrayDeque<>();

        map = new char[r][c];
        Point[] swans = new Point[2];
        int index = 0;

        // 1
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'L') {
                    swans[index++] = new Point(i, j);
                    waterQu.offer(new Point(i, j));
                    map[i][j] = '.';
                } else if (map[i][j] == '.') {
                    waterQu.offer(new Point(i, j));
                }
            }
        }

        // 2
        Point p = swans[0];
        swanQu.offer(p);

        boolean[][] visit = new boolean[r][c];
        visit[p.x][p.y] = true;

        int days = 0;
        // 3
        while (!canMeet(visit, swans[1].x, swans[1].y)) {
            melting();
            days++;
        }
        System.out.println(days);
    }

    // 4
    private static boolean canMeet(boolean[][] visit, int ex, int ey) {
        while (!swanQu.isEmpty()) {
            Point p = swanQu.poll();
            int x = p.x;
            int y = p.y;
            if (x == ex && y == ey) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c || visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;

                if (map[nx][ny] == '.') {
                    swanQu.offer(new Point(nx, ny));
                }
                else if (map[nx][ny] == 'X') {
                    nextSwanQu.offer(new Point(nx, ny));
                }
            }
        }

        swanQu = nextSwanQu;
        nextSwanQu = new ArrayDeque<>();
        return false;
    }

    // 5
    private static void melting() {
        while (!waterQu.isEmpty()) {
            Point p = waterQu.poll();
            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c || map[nx][ny] != 'X') {
                    continue;
                }

                nextWaterQu.offer(new Point(nx, ny));
                map[nx][ny] = '.';
            }
        }

        waterQu = nextWaterQu;
        nextWaterQu = new ArrayDeque<>();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
