package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1256">백준 1256번 - DP : 사전</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1256%EB%B2%88-%EC%82%AC%EC%A0%84">velog</a>
 * @since 2025-01-06
 */
public class BJ_1256 {

    static long[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        dp = new long[n + m + 1][n + m + 1];

        for (int i = 0; i <= n + m; i++) {
            dp[i][0] = dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dp[i - 1][j - 1], 1_000_000_001);
            }
        }

        if (k > dp[n + m][n]) {
            System.out.println(-1);
            return;
        }

        System.out.println(solve(n, m, k));
    }

    private static String solve(int n, int m, long k) {

        //N개 또는 M개 문자를 다 소진했을 경우, 남은 문자로 뒤를 채운다.
        if (n == 0) return "z".repeat(m);
        if (m == 0) return "a".repeat(n);

        //현재 "a"를 선택했을 때 만들 수 있는 문자열의 개수
        long a = dp[n + m - 1][n - 1];

        if (k <= a) {
            return "a" + solve(n - 1, m, k);
        } else {
            return "z" + solve(n, m - 1, k - a);
        }
    }
}