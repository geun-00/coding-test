package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2169">백준 2169번 - DP : 로봇 조종하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2169%EB%B2%88-%EB%A1%9C%EB%B4%87-%EC%A1%B0%EC%A2%85%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-12-19
 */
public class BJ_2169 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];

        //첫번째 행 처리
        for (int i = 1; i <= m; i++) {
            dp[1][i] = dp[1][i - 1] + arr[1][i];
        }

        //0 = 왼쪽에서 오른쪽
        //1 = 오른쪽에서 왼쪽
        int[][] temp = new int[2][m + 2];

        //2번째 행 ~ N번째 행
        for (int i = 2; i <= n; i++) {

            //왼쪽에서 오른쪽 ->
            temp[0][0] = Integer.MIN_VALUE;
            for (int j = 1; j <= m; j++) {
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + arr[i][j];
            }

            //오른쪽에서 왼쪽 <-
            temp[1][m + 1] = Integer.MIN_VALUE;
            for (int j = m; j >= 1; j--) {
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + arr[i][j];
            }

            //두 가지 방향 중 최댓값
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }

        System.out.println(dp[n][m]);
    }
}