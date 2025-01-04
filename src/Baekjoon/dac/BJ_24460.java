package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/24460">백준 24460번 - 분할 정복 : 특별상이라도 받고 싶어</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-24460%EB%B2%88-%ED%8A%B9%EB%B3%84%EC%83%81%EC%9D%B4%EB%9D%BC%EB%8F%84-%EB%B0%9B%EA%B3%A0-%EC%8B%B6%EC%96%B4">velog</a>
 * @since 2025-01-01
 */
public class BJ_24460 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(n, 0, 0, arr));
    }

    private static int solve(int n, int x, int y, int[][] arr) {

        if (n == 1) {
            return arr[x][y];
        }

        int half = n / 2;

        int[] temp = {
            solve(half, x, y, arr), //왼쪽 위
            solve(half, x, y + half, arr), //오른쪽 위
            solve(half, x + half, y, arr), //왼쪽 아래
            solve(half, x + half, y + half, arr) //오른쪽 아래
        };

        Arrays.sort(temp);

        return temp[1];
    }
}