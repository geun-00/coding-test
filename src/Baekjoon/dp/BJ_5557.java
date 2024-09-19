package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5557">백준 5557번 - DP : 1학년</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5557%EB%B2%88-1%ED%95%99%EB%85%84">velog</a>
 *
 * @since 2024-09-12
 */
public class BJ_5557 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n + 1][21];

        //첫번째 숫자 초기화
        dp[1][num[1]] = 1;

        for (int i = 2; i < n; i++) {
            for (int j = 0; j <= 20; j++) {
                //이전 번째에 가능한 경우의 수가 있다면
                if (dp[i - 1][j] > 0) {
                    if (j + num[i] <= 20) {
                        dp[i][j + num[i]] += dp[i - 1][j];
                    }
                    if (j - num[i] >= 0) {
                        dp[i][j - num[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[n - 1][num[n]]);
    }
}
