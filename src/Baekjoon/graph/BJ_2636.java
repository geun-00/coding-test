package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2636">백준 2636번 - 그래프 탐색 : 치즈</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2636%EB%B2%88-%EC%B9%98%EC%A6%88">velog</a>
 * @since 2024-08-01
 */
public class BJ_2636 {
    static int[][] map;
    static int n, m;
    static int cheese = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
        int cnt = 0;

        while (cheese > 0) {
            time++;
            cnt = cheese;
            bfs();
        }
        System.out.println(time);
        System.out.println(cnt);
    }

    private static void bfs() {

        boolean[][] visit = new boolean[n][m];
        Queue<Point> qu = new ArrayDeque<>();

        visit[0][0] = true;
        qu.offer(new Point(0, 0));

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        qu.offer(new Point(nx, ny));
                    } else {
                        map[nx][ny] = 0;
                        cheese--;
                    }
                    visit[nx][ny] = true;
                }
            }
        }
    }
}
