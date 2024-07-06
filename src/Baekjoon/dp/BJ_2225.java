package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2225">백준 2225번 - DP : 합분해</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2225%EB%B2%88-%ED%95%A9%EB%B6%84%ED%95%B4">velog</a>
 * @since 2024-07-05
 */

public class BJ_2225 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];

        //0으로 합이 0이 되기 위한 경우의 수
        for (int i = 1; i <= k; i++) {
            dp[0][i] = 1;
        }

        //숫자 1개로 n을 만드는 경우의 수
        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[n][k]);
    }
}
