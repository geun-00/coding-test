package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1311">백준 1311번 - 비트 마스킹 : 할 일 정하기 1</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1311%EB%B2%88-%ED%95%A0-%EC%9D%BC-%EC%A0%95%ED%95%98%EA%B8%B0-1">velog</a>
 * @since 2025-02-25
 */
public class BJ_1311 {

    static int n;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(0, 0));
    }

    private static int solve(int row, int visit) {
        if (visit == (1 << n) - 1) return 0;
        if (dp[row][visit] != -1) return dp[row][visit];

        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) != 0) continue;
            minCost = Math.min(minCost, solve(row + 1, visit | (1 << i)) + cost[row][i]);
        }

        return dp[row][visit] = minCost;
    }
}
