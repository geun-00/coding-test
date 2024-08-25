package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17404">백준 17404번 - DP : RGB거리 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17404%EB%B2%88-RGB%EA%B1%B0%EB%A6%AC-2">velog</a>
 *
 * @since 2024-08-19
 */
public class BJ_17404 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][3];
        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) { //1번 집의 색깔

            //1번 집 최소 비용 설정
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    dp[0][j] = cost[0][j];
                } else {
                    dp[0][j] = 1000 * 1000;
                }
            }

            //인접한 집끼리 서로 다른 색깔로 칠해야 한다.
            for (int j = 1; j < n; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2];
            }

            //마지막 집에서 1번 집과 다른 색깔일 때 최소비용 비교 후 갱신
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    min = Math.min(min, dp[n - 1][j]);
                }
            }
        }

        System.out.println(min);
    }
}
