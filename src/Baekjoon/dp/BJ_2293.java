package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2293">백준 2293번 - DP : 동전 1</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2293%EB%B2%88-%EB%8F%99%EC%A0%84-1">velog</a>
 * @since 2024-06-22
 */
public class BJ_2293 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[k + 1][n + 1];
        int[] value = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(br.readLine());
            dp[0][i] = 1; //0원을 고르는 경우의 수 1로 초기화
        }

        for (int i = 1; i <= k; i++) { //1원 ~ k원
            for (int j = 1; j <= n; j++) {

                if (i - value[j] >= 0) { //현재 동전의 가치로 현재 목표 금액을 구성할 수 있다면
                    dp[i][j] = dp[i - value[j]][j] + dp[i][j - 1];
                } else { //현재 동전의 가치가 현재 목표 금액보다 높다면
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[k][n]);
    }
}
