package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12100">백준 12100번 - 2048 (Easy)</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12100%EB%B2%88-2048Easy">velog</a>
 *
 * @since 2024-10-25
 */
public class BJ_12100 {

    static int[][] board;
    static int n;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0);
        System.out.println(max);
    }

    private static void solve(int depth) {

        //depth==이동한 횟수, 5번 이동시켜 가장 큰 블록 구하기
        if (depth == 5) {
            getMax();
            return;
        }

        int[][] temp = new int[n][n];

        //현재 보드 상태 임시 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < 4; i++) {
            move(i);
            solve(depth + 1);

            //5번 이동하면 이전 보드 상태 복구하고 다른 방향으로 이동 시도
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    board[j][k] = temp[j][k];
                }
            }
        }
    }

    private static void move(int d) {

        switch (d) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
        }
    }

    private static void moveRight() {

        for (int i = 0; i < n; i++) {

            Queue<Integer> qu = new ArrayDeque<>();

            for (int j = n - 1; j >= 0; j--) {
                if (board[i][j] == 0) continue;
                qu.offer(board[i][j]);
            }

            int idx = n - 1;

            while (!qu.isEmpty()) {
                int now = qu.poll();

                if (!qu.isEmpty() && now == qu.peek()) {
                    now *= 2;
                    qu.poll();
                }

                board[i][idx--] = now;
            }

            while (idx >= 0) {
                board[i][idx--] = 0;
            }
        }
    }

    private static void moveLeft() {

        for (int i = 0; i < n; i++) {

            Queue<Integer> qu = new ArrayDeque<>();

            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;
                qu.offer(board[i][j]);
            }

            int idx = 0;

            while (!qu.isEmpty()) {
                int now = qu.poll();

                if (!qu.isEmpty() && now == qu.peek()) {
                    now *= 2;
                    qu.poll();
                }

                board[i][idx++] = now;
            }

            while (idx < n) {
                board[i][idx++] = 0;
            }
        }
    }

    private static void moveDown() {

        for (int i = 0; i < n; i++) {

            Queue<Integer> qu = new ArrayDeque<>();

            for (int j = n - 1; j >= 0; j--) {
                if (board[j][i] == 0) continue;
                qu.offer(board[j][i]);
            }

            int idx = n - 1;

            while (!qu.isEmpty()) {
                int now = qu.poll();

                if (!qu.isEmpty() && now == qu.peek()) {
                    now *= 2;
                    qu.poll();
                }

                board[idx--][i] = now;
            }

            while (idx >= 0) {
                board[idx--][i] = 0;
            }
        }
    }

    private static void moveUp() {

        for (int i = 0; i < n; i++) {

            Queue<Integer> qu = new ArrayDeque<>();

            for (int j = 0; j < n; j++) {
                if (board[j][i] == 0) continue;
                qu.offer(board[j][i]);
            }

            int idx = 0;

            while (!qu.isEmpty()) {
                int now = qu.poll();

                if (!qu.isEmpty() && now == qu.peek()) {
                    now *= 2;
                    qu.poll();
                }

                board[idx++][i] = now;
            }

            while (idx < n) {
                board[idx++][i] = 0;
            }
        }
    }

    private static void getMax() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
    }
}
