package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2116">백준 2116번 - 구현 : 주사위 쌓기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2116%EB%B2%88-%EC%A3%BC%EC%82%AC%EC%9C%84-%EC%8C%93%EA%B8%B0">velog</a>
 * @since 2025-02-14
 */
public class BJ_2116 {

    static int[][] dice;
    static int[] opposite = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dice = new int[n][6];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < 6; i++) {
            int maxSide = 0;
            for (int j = 0; j < 6; j++) {
                if (i == j || i == opposite[j]) continue;
                maxSide = Math.max(maxSide, dice[0][j]);
            }

            ans = Math.max(ans, solve(1, maxSide, dice[0][i]));
        }

        System.out.println(ans);
    }

    private static int solve(int depth, int sum, int prevTop) {
        if (depth == dice.length) {
            return sum;
        }

        int nextBottomIndex = -1;
        for (int i = 0; i < 6; i++) {
            if (dice[depth][i] == prevTop) {
                nextBottomIndex = i;
                break;
            }
        }

        int topIndex = opposite[nextBottomIndex];
        int maxSide = 0;
        for (int i = 0; i < 6; i++) {
            if (i == nextBottomIndex || i == topIndex) continue;
            maxSide = Math.max(maxSide, dice[depth][i]);
        }

        return solve(depth + 1, sum + maxSide, dice[depth][topIndex]);
    }
}
