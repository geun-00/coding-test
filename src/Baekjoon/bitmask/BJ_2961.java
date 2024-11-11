package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2961">백준 2961번 - 비트 마스킹 : 도영이가 만든 맛있는 음식</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2961%EB%B2%88-%EB%8F%84%EC%98%81%EC%9D%B4%EA%B0%80-%EB%A7%8C%EB%93%A0-%EB%A7%9B%EC%9E%88%EB%8A%94-%EC%9D%8C%EC%8B%9D">velog</a>
 * @since 2024-11-10
 */
public class BJ_2961 {

    static int[][] arr;
    static int n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //bit-mask
        for (int i = 1; i < (1 << n); i++) {

            int s = 1;
            int b = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    s *= arr[j][0];
                    b += arr[j][1];
                }
            }

            min = Math.min(min, Math.abs(s - b));
        }

        //backTrack
        for (int i = 1; i <= n; i++) {
            int[] pick = new int[i];
            solve(0, 0, i, pick);
        }

        System.out.println(min);
    }

    private static void solve(int start, int depth, int pickNum, int[] pick) {

        if (depth == pickNum) {
            min = Math.min(min, getDiff(pick));
            return;
        }

        for (int i = start; i < n; i++) {
            pick[depth] = i;
            solve(i + 1, depth + 1, pickNum, pick);
        }
    }

    private static int getDiff(int[] pick) {

        int s = 1;
        int b = 0;

        for (int p : pick) {
            s *= arr[p][0];
            b += arr[p][1];
        }

        return Math.abs(s - b);
    }
}
