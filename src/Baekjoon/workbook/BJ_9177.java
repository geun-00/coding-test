package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9177">백준 9177번 - 단어 섞기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9177%EB%B2%88-%EB%8B%A8%EC%96%B4-%EC%84%9E%EA%B8%B0">velog</a>
 * @since 2025-03-22
 */
public class BJ_9177 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= n; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] a = st.nextToken().toCharArray();
            char[] b = st.nextToken().toCharArray();
            char[] c = st.nextToken().toCharArray();

            int lenA = a.length;
            int lenB = b.length;

            // 1
            boolean[][] dp = new boolean[lenA + 1][lenB + 1];
            dp[0][0] = true;

            // 2
            for (int i = 1; i <= lenA; i++) dp[i][0] = dp[i - 1][0] && a[i - 1] == c[i - 1];
            for (int i = 1; i <= lenB; i++) dp[0][i] = dp[0][i - 1] && b[i - 1] == c[i - 1];

            // 3
            for (int i = 1; i <= lenA; i++) {
                for (int j = 1; j <= lenB; j++) {
                    dp[i][j] =
                        (dp[i - 1][j] && a[i - 1] == c[i + j - 1]) ||
                        (dp[i][j - 1] && b[j - 1] == c[i + j - 1]);
                }
            }

            // 4
            sb.append("Data set ")
              .append(tc)
              .append(": ")
              .append(dp[lenA][lenB] ? "yes" : "no")
              .append("\n");
        }

        System.out.print(sb);
    }
}
