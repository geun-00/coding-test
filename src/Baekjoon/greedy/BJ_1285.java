package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/1285">백준 1285번 - 그리디 : 동전 뒤집기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1285%EB%B2%88-%EB%8F%99%EC%A0%84-%EB%92%A4%EC%A7%91%EA%B8%B0">velog</a>
 * @since 2024-12-06
 */
public class BJ_1285 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

/*
        int[] arr = new int[n];
        int ans = n * n;

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (chars[j] == 'T') {
                    arr[i] += (1 << j);
                }
            }
        }

        for (int i = 0; i < (1 << n); i++) {

            int sum = 0;

            for (int j = 0; j < n; j++) {

                int temp = Integer.bitCount(arr[j] ^ i);
                sum += Math.min(temp, n - temp);
            }

            ans = Math.min(ans, sum);
        }

        System.out.println(ans);
*/
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {

            char[] chars = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                arr[i][j] = (chars[j] == 'H') ? 0 : 1;
            }
        }

        int ans = n * n;

        for (int bit = 0; bit < (1 << n); bit++) {
            turnRows(arr, n, bit);
            ans = Math.min(ans, solve(arr, n));
            turnRows(arr, n, bit);
        }

        System.out.println(ans);
    }

    private static void turnRows(int[][] arr, int n, int bit) {

        for (int i = 0; i < n; i++) {
            if (((bit & (1 << i)) != 0)) {

                for (int j = 0; j < n; j++) {

                    arr[i][j] = 1 - arr[i][j];
                }
            }

        }
    }

    private static int solve(int[][] arr, int n) {

        int total = 0;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = 0; j < n; j++) {
                if (arr[j][i] == 1) {
                    count++;
                }
            }

            total += Math.min(count, n - count);
        }

        return total;
    }
}