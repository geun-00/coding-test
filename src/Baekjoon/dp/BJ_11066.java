package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11066">백준 11066번 - DP : 파일 합치기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11066%EB%B2%88-%ED%8C%8C%EC%9D%BC-%ED%95%A9%EC%B9%98%EA%B8%B0">velog</a>
 * @since 2024-08-01
 */
public class BJ_11066 {

    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            int k = Integer.parseInt(br.readLine());

            sum = new int[k + 1]; //누적합
            dp = new int[k + 1][k + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i], -1);
            }

            System.out.println(solve(1, k));
        }
    }

    private static int solve(int s, int e) {
        if (s == e) {
            return dp[s][e] = 0;
        }
        if (dp[s][e] != -1) {
            return dp[s][e];
        }

        dp[s][e] = Integer.MAX_VALUE;
        //중간을 바꿔가면서 합한 값과 전체 구간의 비용을 더한다.
        for (int m = s; m < e; m++) {
            int temp = solve(s, m) + solve(m + 1, e) + sum[e] - sum[s - 1];
            dp[s][e] = Math.min(dp[s][e], temp);
        }

        return dp[s][e];
    }
}
