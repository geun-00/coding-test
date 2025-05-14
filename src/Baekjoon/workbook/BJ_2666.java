package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2666">백준 2666번 - 벽장문의 이동</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2666%EB%B2%88-%EB%B2%BD%EC%9E%A5%EB%AC%B8%EC%9D%98-%EC%9D%B4%EB%8F%99">velog</a>
 * @since 2025-04-24
 */
public class BJ_2666 {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;

        int m = Integer.parseInt(br.readLine());

        // 1
        int[][][] dp = new int[m + 1][n][n];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[0][a][b] = dp[0][b][a] = 0;

        // 2
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine()) - 1;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dp[i][j][k] == INF) continue;

                    int now = dp[i][j][k];

                    // 3
                    if (num == j || num == k) {
                        // 이전 순서에서 이미 벽장이 열려있는 경우
                        dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], now);
                    } else {
                        // j번 벽장을 이동
                        dp[i + 1][num][k] = Math.min(dp[i + 1][num][k], now + Math.abs(num - j));
                        // k번 벽장을 이동
                        dp[i + 1][j][num] = Math.min(dp[i + 1][j][num], now + Math.abs(num - k));
                    }
                }
            }
        }

        // 4
        int ans = INF;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.min(ans, dp[m][i][j]);
            }
        }

        System.out.println(ans);
    }
}
