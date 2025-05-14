package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2616">백준 2616번 - DP : 소형기관차</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2616%EB%B2%88-%EC%86%8C%ED%98%95%EA%B8%B0%EA%B4%80%EC%B0%A8">velog</a>
 * @since 2025-05-06
 */
public class BJ_2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 1
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        // 2
        int[][] dp = new int[4][n + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = m; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - m] + (arr[j] - arr[j - m]));
            }
        }

        System.out.println(dp[3][n]);
    }
}
