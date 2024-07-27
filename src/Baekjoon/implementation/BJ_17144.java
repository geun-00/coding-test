package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17144">백준 17144번 - 구현 : 미세먼지 안녕!</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17144%EB%B2%88-%EB%AF%B8%EC%84%B8%EB%A8%BC%EC%A7%80-%EC%95%88%EB%85%95">velog</a>
 * @since 2024-07-26
 */
public class BJ_17144 {

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

            getDusts(); //미세먼지 정보 저장

            spread(); //미세먼지 확산

            clean(); //공기청정기 작동
        }

        //결과 출력
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

    private static void getDusts() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] >= 5) {
                    dusts.offer(new Dust(i, j, map[i][j]));
                }
            }
        }
    }

    private static void spread() {

        while (!dusts.isEmpty()) {

            Dust dust = dusts.poll();

            int x = dust.x;
            int y = dust.y;

            int spreadAmount = dust.amount / 5; //주변에 확산되는 미세먼지 양
            int count = 0;                      //확산된 방향의 개수

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