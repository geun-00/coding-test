package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1727">백준 1727번 - 커플 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1727%EB%B2%88-%EC%BB%A4%ED%94%8C-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2025-04-16
 */
public class BJ_1727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] men = new int[n];
        int[] women = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) men[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) women[i] = Integer.parseInt(st.nextToken());

        // 1
        Arrays.sort(men);
        Arrays.sort(women);

        int[][] dp = new int[n + 1][m + 1];

        // 2
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int diff = Math.abs(men[i - 1] - women[j - 1]); //성격의 차이

                // 3
                if (i == j) dp[i][j] = dp[i - 1][j - 1] + diff;
                else if (i < j) dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + diff);
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + diff);
            }
        }

        System.out.println(dp[n][m]);
    }
}
