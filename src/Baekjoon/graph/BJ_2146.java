package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2146">백준 2146번 - 그래프 탐색 : 다리 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2146%EB%B2%88-%EB%8B%A4%EB%A6%AC-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2024-10-01
 */
public class BJ_2146 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int n;

    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //대륙을 구분시키는 작업
        visit = new boolean[n][n];
        int num = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    areaBFS(i, j, num++);
                }
            }
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {

                    int len = bfs(i, j);
                    if (len > 0) {
                        result = Math.min(result, len);
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static int bfs(int x, int y) {

        visit = new boolean[n][n];
        visit[x][y] = true;

        int myArea = map[x][y];

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{x, y, 0});

        while (!qu.isEmpty()) {

            int[] now = qu.poll();

            int len = now[2];

            //바다가 아닌 다른 섬의 땅에 도착했을 때 이동 거리 반환
            if (map[now[0]][now[1]] > 0 && map[now[0]][now[1]] != myArea) {
                return len - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visit[nx][ny] && map[nx][ny] != myArea) {
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny, len + 1});
                    }
                }
            }
        }

        return 0;
    }

    private static void areaBFS(int x, int y, int num) {

        visit[x][y] = true;
        map[x][y] = num;

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visit[nx][ny] && map[nx][ny] == 1) {
                        visit[nx][ny] = true;
                        map[nx][ny] = num;
                        qu.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }
}
