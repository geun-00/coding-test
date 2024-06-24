package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1520">백준 1520번 - 내리막 길</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1520%EB%B2%88-%EB%82%B4%EB%A6%AC%EB%A7%89-%EA%B8%B8">velog</a>
 * @since 2024-06-24
 */
public class BJ_1520 {

    static int[][] map;
    static int m, n;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) { //도착 지점 가능, 경우의 수 추가
            return 1;
        }

        if (dp[x][y] != -1) { //dp 배열을 방문 체크의 용도로도 가능하다.
            return dp[x][y];
        }

        dp[x][y] = 0; //가능한 경로 방문 확인

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) { //범위 체크
                if (map[nx][ny] < map[x][y]) { //조건 체크
                    dp[x][y] += dfs(nx, ny); //현재 위치에서 가능한 경로의 개수 합
                }
            }
        }

        return dp[x][y];
    }
}
