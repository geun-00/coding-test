package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/1509">백준 1509번 - DP : 팰린드롬 분할</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1509%EB%B2%88-%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC-%EB%B6%84%ED%95%A0">velog</a>
 * @since 2024-11-19
 */
public class BJ_1509 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        int n = arr.length;

        boolean[][] isPalindrome = new boolean[n][n];

        //길이 1~2 팰린드롬
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;

            if (i < n - 1 && arr[i] == arr[i + 1]) {
                isPalindrome[i][i + 1] = true;
            }
        }

        //길이 3~N 팰린드롬
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {

                if (arr[i] == arr[j] && isPalindrome[i + 1][j - 1]) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {

            if (isPalindrome[0][i]) {
                dp[i] = 1;
            }
            else {
                dp[i] = Integer.MAX_VALUE;

                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}