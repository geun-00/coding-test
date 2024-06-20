package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/9251">백준 9251번 - DP : LCS</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9251%EB%B2%88-LCS">velog</a>
 * @since 2024-06-20
 */
public class BJ_9251 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int lenA = A.length;
        int lenB = B.length;

        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {

                if (A[i - 1] == B[j - 1]) { //두 문자가 같으면 왼쪽 대각선 + 1 저장
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {                    //두 문자가 다르면 왼쪽과 위쪽 값 중 큰 값 저장
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[lenA][lenB]);
    }
}
