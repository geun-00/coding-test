package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20061">백준 20061번 - 모노미노도미노 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20061%EB%B2%88-%EB%AA%A8%EB%85%B8%EB%94%94%EB%AA%A8%EB%85%B8%EB%94%94%EB%AA%A8-2">velog</a>
 * @since 2025-04-21
 */
public class BJ_20061 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int score = 0;
        // 1
        int[][] board = new int[10][10];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 2
            if (t == 1) {
                int ty = y;
                while (ty + 1 < 10 && board[x][ty + 1] == 0) ty++;
                board[x][ty] = 1;

                while (x + 1 < 10 && board[x + 1][y] == 0) x++;
                board[x][y] = 1;
            }
            else if (t == 2) {
                int ty = y + 1;
                while (ty + 1 < 10 && board[x][ty + 1] == 0) ty++;
                board[x][ty] = board[x][ty - 1] = 1;

                while (x + 1 < 10 && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0) x++;
                board[x][y] = board[x][y + 1] = 1;
            }
            else if (t == 3) {
                int tx = x + 1;
                while (tx + 1 < 10 && board[tx + 1][y] == 0) tx++;
                board[tx - 1][y] = board[tx][y] = 1;

                while (y + 1 < 10 && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0) y++;
                board[x][y] = board[x + 1][y] = 1;
            }

            // 3
            score += cleanAndDrop(board);

            // 4
            move(board);
        }

        // 5
        System.out.println(score);
        System.out.println(getTiles(board));
    }

    // 3
    private static int cleanAndDrop(int[][] board) {
        int count = 0;

        //초록색
        for (int row = 9; row >= 6; row--) {
            boolean flag = true;
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
                for (int i = row; i > 4; i--) {
                    for (int j = 0; j < 4; j++) {
                        board[i][j] = board[i - 1][j];
                    }
                }

                for (int i = 0; i < 4; i++) {
                    board[4][i] = 0;
                }
                row++; //포인트
            }
        }

        //파란색
        for (int col = 9; col >= 6; col--) {
            boolean flag = true;
            for (int row = 0; row < 4; row++) {
                if (board[row][col] == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
                for (int i = col; i > 4; i--) {
                    for (int j = 0; j < 4; j++) {
                        board[j][i] = board[j][i - 1];
                    }
                }
                for (int i = 0; i < 4; i++) {
                    board[i][4] = 0;
                }
                col++; //포인트
            }
        }

        return count;
    }

    // 4
    private static void move(int[][] board) {
        //초록색
        int count = 0;
        for (int row = 4; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 1) {
                    count++;
                    break;
                }
            }
        }

        for (int i = 0; i < count; i++) {

            for (int row = 9; row >= 5; row--) {
                for (int col = 0; col < 4; col++) {
                    board[row][col] = board[row - 1][col];
                }
            }
            for (int col = 0; col < 4; col++) {
                board[4][col] = 0;
            }
        }

        //파란색
        count = 0;
        for (int col = 4; col < 6; col++) {
            for (int row = 0; row < 4; row++) {
                if (board[row][col] == 1) {
                    count++;
                    break;
                }
            }
        }

        for (int i = 0; i < count; i++) {

            for (int col = 9; col >= 5; col--) {
                for (int row = 0; row < 4; row++) {
                    board[row][col] = board[row][col - 1];
                }
            }

            for (int row = 0; row < 4; row++) {
                board[row][4] = 0;
            }
        }
    }

    // 5
    private static int getTiles(int[][] board) {
        int count = 0;

        for (int row = 4; row <= 9; row++) {
            for (int col = 0; col < 4; col++) {
                count += board[row][col];
            }
        }

        for (int col = 4; col <= 9; col++) {
            for (int row = 0; row < 4; row++) {
                count += board[row][col];
            }
        }

        return count;
    }
}
