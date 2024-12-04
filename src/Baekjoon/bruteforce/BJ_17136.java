package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17136">백준 17136번 - 브루트포스 : 색종이 붙이기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17136%EB%B2%88-%EC%83%89%EC%A2%85%EC%9D%B4-%EB%B6%99%EC%9D%B4%EA%B8%B0">velog</a>
 * @since 2024-11-25
 */
public class BJ_17136 {

    static final int INF = Integer.MAX_VALUE;
    static final int SIZE = 10;

    static int ans = INF;
    static int[][] arr = new int[SIZE][SIZE];
    static int[] paper = new int[5];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            paper[i] = 5;
        }

        solve(0, 0, 0);

        System.out.println(ans == INF ? -1 : ans);
    }

    private static void solve(int x, int y, int count) {

        //모든 1을 덮었다면 종료
        if (x == SIZE) {
            ans = Math.min(ans, count);
            return;
        }

        //현재 행을 모두 탐색했다면 다음 행 호출
        if (y == SIZE) {
            solve(x + 1, 0, count);
            return;
        }

        //이미 0이라면 다음 위치(열) 호출
        if (arr[x][y] == 0) {
            solve(x, y + 1, count);
            return;
        }

        for (int i = 0; i < 5; i++) {

            //색종이가 남아있고, 해당 크기로 덮을 수 있는지 확인
            if (paper[i] > 0 && check(x, y, i + 1)) {

                attach(x, y, i + 1, 0);
                paper[i]--;

                solve(x, y + 1, count + 1);

                attach(x, y, i + 1, 1);
                paper[i]++;
            }
        }
    }

    private static boolean check(int x, int y, int size) {

        if (x + size > SIZE || y + size > SIZE) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (arr[x + i][y + j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void attach(int x, int y, int size, int num) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[x + i][y + j] = num;
            }
        }
    }
}