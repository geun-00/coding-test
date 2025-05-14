package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <a href = "https://www.acmicpc.net/problem/1029">백준 1029번 - 비트 마스킹 : 그림 교환</a>
 * <br>
 * <a href = "">velog</a>
 * @since 2025-05-12
 */
public class BJ_1029 {

    static int n;
    static char[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        System.out.println(solve(0, 1, 0));
    }

    private static int solve(int row, int visit, int price) {
        if (dp[row][visit] != 0) return dp[row][visit];

        dp[row][visit] = 1;

        for (int i = 0; i < n; i++) {
            if (((visit & (1 << i)) == 0) && arr[row][i] - '0' >= price) {
                dp[row][visit] = Math.max(
                    dp[row][visit],
                    1 + solve(i, visit | (1 << i), arr[row][i] - '0')
                );
            }
        }

        return dp[row][visit];
    }
}
