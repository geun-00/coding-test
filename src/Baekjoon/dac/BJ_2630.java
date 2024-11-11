package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2630">백준 2630번 - 분할 정복 : 색종이 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2630%EB%B2%88-%EC%83%89%EC%A2%85%EC%9D%B4-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2024-11-11
 */
public class BJ_2630 {

    static int[][] arr;
    static int white = 0, blue = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, n);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void solve(int x, int y, int n) {

        //더 이상 나눌 수 없는 경우
        if (n == 1) {
            int c = arr[x][y];
            if (c == 0) white++;
            if (c == 1) blue++;
            return;
        }

        int w = 0, b = 0;

        //현재 나눠진 칸의 칠해진 색깔 세기
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] == 0) {
                    w++;
                } else {
                    b++;
                }
            }
        }

        //모두 같은 색으로 칠해져 있기 때문에 더 나눌 필요 없다.
        if (w == 0 || b == 0) {
            if (w == 0) blue++;
            if (b == 0) white++;
            return;
        }

        int size = n / 2;

        //분할
        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                solve(i, j, size);
            }
        }
    }
}