package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * <a href = "https://www.acmicpc.net/problem/15989">백준 15989번 - DP : 1, 2, 3 더하기 4</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-15989%EB%B2%88-1-2-3-%EB%8D%94%ED%95%98%EA%B8%B0-4">velog</a>
 * @since 2024-10-21
 */
public class BJ_15989 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][10001];

        dp[1][1] = 1;

        dp[1][2] = 1;
        dp[2][2] = 1;

        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 10000; i++) {
            dp[1][i] = dp[1][i - 1];
            dp[2][i] = dp[1][i - 2] + dp[2][i - 2];
            dp[3][i] = dp[1][i - 3] + dp[2][i - 3] + dp[3][i - 3];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[1][n] + dp[2][n] + dp[3][n] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
