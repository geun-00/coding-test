package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2573">백준 2573번 - 그래프 탐색 : 빙산</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2573%EB%B2%88-%EB%B9%99%EC%82%B0">velog</a>
 * @since 2024-07-20
 */
public class BJ_2573 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visit;
    static int n, m;

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
            }
        }

        int years = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    years++;
                    //bfs를 통해 빙산을 녹이고 난 후 덩어리 개수를 반환
                    if (bfs(i, j) >= 2) {
                        System.out.println(years);
                        return;
                    }
                }
            }
        }

        //빙산이 다 녹을 때까지 분리되지 않는 경우
        System.out.println(0);
    }

    private static int bfs(int x, int y) {

        /**
         * 빙산 녹이기 작업
         */
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        visit = new boolean[n][m];
        visit[x][y] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            int count = 0; //주변 바닷물의 개수

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!visit[nx][ny]) {
                    if (map[nx][ny] > 0) { //주변에 다른 빙산이 있는 경우
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                    } else { //주변에 바닷물이 있는 경우
                        count++;
                    }
                }
            }

            //주변 바닷물의 개수만큼 빙산의 높이 감소
            map[now.x][now.y] -= count;
        }

        /**
         * 덩어리 개수 세기 작업
         */
        visit = new boolean[n][m];

        int lumps = 0; //덩어리 수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && !visit[i][j]) {
                    lumps++;
                    bfsLumps(i, j);
                }
            }
        }

        return lumps;
    }

    private static void bfsLumps(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        visit[x][y] = true;

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!visit[nx][ny] && map[nx][ny] > 0) {
                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny));
                }
            }
        }
    }
}
