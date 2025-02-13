package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10830">백준 10830번 - 분할 정복 : 행렬 제곱</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10830%EB%B2%88-%ED%96%89%EB%A0%AC-%EC%A0%9C%EA%B3%B1">velog</a>
 * @since 2025-02-10
 */
public class BJ_10830 {

    final static int MOD = 1000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = solve(arr, b);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j] % MOD).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[][] solve(int[][] arr, long b) {
        if (b == 1L) {
            return arr;
        }

        int[][] half = solve(arr, b / 2);
        int[][] result = multiply(half, half);

        if (b % 2 == 1L) {
            result = multiply(result, arr);
        }

        return result;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    temp[i][j] += a[i][k] * b[k][j];
                    temp[i][j] %= MOD;
                }
            }
        }

        return temp;
    }
}
