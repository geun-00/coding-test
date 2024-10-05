package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/5582">백준 5582번 - DP : 공통 부분 문자열</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5582%EB%B2%88-%EA%B3%B5%ED%86%B5-%EB%B6%80%EB%B6%84-%EB%AC%B8%EC%9E%90%EC%97%B4">velog</a>
 *
 * @since 2024-09-29
 */
public class BJ_5582 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int lenA = a.length;
        int lenB = b.length;

        int[][] dp = new int[lenA + 1][lenB + 1];

        int max = 0;

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
    }
}
