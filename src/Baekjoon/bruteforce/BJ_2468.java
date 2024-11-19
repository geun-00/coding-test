package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2468">백준 2468번 - 브루트포스 : 안전 영역</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2468%EB%B2%88-%EC%95%88%EC%A0%84-%EC%98%81%EC%97%AD">velog</a>
 * @since 2024-11-13
 */
public class BJ_2468 {

    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int max = 0;

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                max = Math.max(max, map[i][j]);
            }
        }

        int ans = 0;

        for (int i = 0; i < max; i++) {

            visit = new boolean[n][n];

            //잠기는 지점 미리 방문 처리
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] <= i) {
                        visit[j][k] = true;
                    }
                }
            }

            int count = 0;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!visit[j][k]) {
                        count++;
                        bfs(j, k);
                    }
                }
            }

            ans = Math.max(ans, count);
        }

        System.out.println(ans);
    }

    private static void bfs(int x, int y) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x * n + y);

        visit[x][y] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int i = 0; i < 4; i++) {

                int nx = now / n + dx[i];
                int ny = now % n + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(nx * n + ny);
            }
        }
    }
}