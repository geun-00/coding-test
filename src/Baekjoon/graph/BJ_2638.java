package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2638">백준 2638번 - 그래프 탐색 : 치즈</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2638%EB%B2%88-%EC%B9%98%EC%A6%88">velog</a>
 * @since 2024-08-08
 */
public class BJ_2638 {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cheese = 0;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int time = 0;

        while (cheese > 0) {

            time++;

            /**
             * 외부 공기와 내부 공간을 분리하는 작업
             * 처음 (0, 0)과 연결된 모든 공기는 외부 공기
             * 그 외 따로 연결된 공기는 모두 내부 공기
             */
            boolean outside = true;
            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0 && !visit[i][j]) {
                        markOutside(i, j, outside);
                        outside = false;
                    }
                }
            }

            /**
             * 2변 이상이 외부 공기와 접촉된 치즈 제거 작업
             */
            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        removeCheese(i, j);
                    }
                }
            }

            /**
             * 외부 공기로 표시한 칸 초기화
             */
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == -1) {
                        map[i][j] = 0;
                    }
                }
            }

        }

        System.out.println(time);
    }

    private static void markOutside(int x, int y, boolean outside) {

        Queue<Point> qu = new ArrayDeque<>();
        visit[x][y] = true;
        qu.offer(new Point(x, y));

        int num = outside ? 0 : -1; //외부 공기=0, 내부 공기=-1로 표시

        map[x][y] = num;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (rangeCheck(nx, ny)) {
                    if (!visit[nx][ny] && map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        map[nx][ny] = num;
                        qu.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }

    private static void removeCheese(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();
        visit[x][y] = true;
        qu.offer(new Point(x, y));

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            int count = 0; //외부 공기와 접촉한 변의 수

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                    } else if (map[nx][ny] == 0) {
                        count++;
                    }
                }
            }

            //2변 이상 접촉하면 치즈 제거
            if (count >= 2) {
                map[now.x][now.y] = 0;
                cheese--;
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
