package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14500">백준 14500번 - 구현 : 테트로미노</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14500%EB%B2%88-%ED%85%8C%ED%8A%B8%EB%A1%9C%EB%AF%B8%EB%85%B8">velog</a>
 * @since 2024-06-28
 */
public class BJ_14500 {

    static int[][] map;
    static int n, m;
    static boolean[][] visit;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    dfs(1, i, j, map[i][j]);
                    visit[i][j] = false;

                    dfs(0, 0, i, j, map[i][j]); // ㅓ, ㅗ, ㅏ, ㅜ 모양 탐색
                }
            }
        }

        System.out.println(max);
    }

    private static void dfs(int depth, int x, int y, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(depth + 1, nx, ny, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }

    private static void dfs(int start, int depth, int x, int y, int sum) {
        if (depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                dfs(i + 1, depth + 1, x, y, sum + map[nx][ny]);
            }
        }
    }
}
