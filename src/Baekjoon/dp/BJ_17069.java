package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17069">백준 17069번 - DP : 파이프 옮기기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17069%EB%B2%88-%ED%8C%8C%EC%9D%B4%ED%94%84-%EC%98%AE%EA%B8%B0%EA%B8%B0-2">velog</a>
 * @since 2025-03-07
 */
public class BJ_17069 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] home = new int[n][n];
        long[][][] dp = new long[n][n][3]; //0=가로, 1=세로, 2=대각선
        dp[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //0행
        for (int i = 2; i < n; i++) {
            if (home[0][i] == 0) {
                dp[0][i][0] = dp[0][i - 1][0];
            }
        }

        //1행 ~ N-1행
        for (int i = 1; i < n; i++) {
            //2열 ~ N-1열
            for (int j = 2; j < n; j++) {
                if (home[i][j] == 1) continue;

                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2]; //가로 도착
                dp[i][j][1] = dp[i - 1][j][2] + dp[i - 1][j][1]; //세로 도칙

                //대각선 도착
                if (home[i][j - 1] == 0 && home[i - 1][j] == 0) {
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
    }
}
