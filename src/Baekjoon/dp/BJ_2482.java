package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2482">백준 2482번 - DP : 색상환</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2482%EB%B2%88-%EC%83%89%EC%83%81%ED%99%98">velog</a>
 * @since 2025-01-24
 * N을 선택한 경우에는 1과 N-1을 선택할 수 없으므로 [2, N-2]에서 K-1개를 선택하는 것과 같고
 * N을 선택하지 않은 경우에는 [1, N-1]에서 K개를 선택하는 것과 같습니다.
 *
 * 원형 DP에서 자주 볼 수 있는 테크닉이니 기억해 두시면 좋을 것 같습니다.
 */
public class BJ_2482 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;   //i개 중에 0개
            dp[i][1] = i;   //i개 중에 1개
        }

        for (int i = 4; i <= n; i++) {  //i개 중에
            for (int j = 2; j <= k; j++) {  //j개

                if (j > i / 2) break;

                dp[i][j] = dp[i - 2][j - 1] + dp[i - 1][j];
                dp[i][j] %= 1_000_000_003;
            }
        }

        System.out.println(dp[n][k]);
    }
}
