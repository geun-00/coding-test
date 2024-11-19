package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9465">백준 9465번 - DP : 스티커</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14501%EB%B2%88-%EC%8A%A4%ED%8B%B0%EC%BB%A4">velog</a>
 * @since 2024-11-12
 */
public class BJ_14501 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n + 1][2];
            int[][] dp = new int[n + 1][2];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][0] = arr[1][0];
            dp[1][1] = arr[1][1];

            for (int i = 2; i <= n; i++) {
                dp[i][0] = arr[i][0] + Math.max(dp[i - 1][1], dp[i - 2][1]);
                dp[i][1] = arr[i][1] + Math.max(dp[i - 1][0], dp[i - 2][0]);
            }

            sb.append(Math.max(dp[n][0], dp[n][1])).append("\n");
        }

        System.out.print(sb);
    }
}