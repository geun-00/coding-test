package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2688">백준 2688번 - DP : 줄어들지 않아</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2688%EB%B2%88-%EC%A4%84%EC%96%B4%EB%93%A4%EC%A7%80-%EC%95%8A%EC%95%84">velog</a>
 * @since 2025-05-27
 */
public class BJ_2688 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // 1
        long[][] dp = new long[65][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = i + 1;
        }

        // 2
        for (int i = 2; i <= 64; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n][9]);
        }
    }
}
