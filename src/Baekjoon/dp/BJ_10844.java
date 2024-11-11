package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/10844">백준 10844번 - DP : 쉬운 계단 수</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10844%EB%B2%88-%EC%89%AC%EC%9A%B4-%EA%B3%84%EB%8B%A8-%EC%88%98">velog</a>
 * @since 2024-11-08
 */
public class BJ_10844 {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //길이가 N이고, 마지막이 i인 계단 수
        int[][] dp = new int[n + 1][10];

        //길이가 1
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        //길이가 2 ~ N
        for (int i = 2; i <= n; i++) {
            
            dp[i][0] = dp[i - 1][1];
            dp[i][9] = dp[i - 1][8];

            for (int j = 1; j < 9; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                dp[i][j] %= MOD;
            }
        }

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += dp[n][i];
            sum %= MOD;
        }

        System.out.println(sum);
    }
}