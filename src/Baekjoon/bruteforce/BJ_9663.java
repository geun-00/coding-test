package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/9663">백준 9663번 - 브루트포스 : N-Queen</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9663%EB%B2%88-N-Queen">velog</a>
 * @since 2024-11-03
 */
public class BJ_9663 {

    //i번째 행에 퀸이 배치된 열 번호
    static int[] col;
    static int n;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        col = new int[n];

        solve(0);

        System.out.println(count);
    }

    private static void solve(int idx) {

        if (idx == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {

            col[idx] = i;

            if (check(idx)) {
                solve(idx + 1);
            }
        }
    }

    private static boolean check(int idx) {

        for (int i = 0; i < idx; i++) {

            //다른 행끼리 같은 열 배치 or 행의 차이와 열의 차이가 같음(대각선)
            if (col[idx] == col[i] || Math.abs(col[idx] - col[i]) == Math.abs(idx - i)) {
                return false;
            }
        }

        return true;
    }
}
