package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2239">백준 2239번 - 구현 : 스도쿠</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2239%EB%B2%88-%EC%8A%A4%EB%8F%84%EC%BF%A0">velog</a>
 * @since 2024-09-02
 */
public class BJ_2239 {

    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        solve();

        //결과 출력
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean solve() {

        Point p = findEmpty(); //빈칸 찾기

        if (p == null) { //모든 칸이 채워진 경우
            return true;
        }

        int x = p.x;
        int y = p.y;

        //작은 수부터 대입하기
        for (int i = 1; i <= 9; i++) {

            //현재 빈칸에 해당 숫자를 넣을 수 있는지 확인
            if (check(x, y, i)) {
                board[x][y] = i;

                //작은 수부터 대입해봤을 때 다 채워지면 바로 종료
                if (solve()) {
                    return true;
                }

                //현재 빈칸에 해당 숫자를 넣으면 스도쿠를 완성할 수 없으므로 초기화
                board[x][y] = 0;
            }
        }

        return false;
    }

    private static boolean check(int x, int y, int num) {

        //행 확인
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num) {
                return false;
            }
        }

        //열 확인
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == num) {
                return false;
            }
        }

        //3x3 사각형 확인
        int start_x = x - x % 3;
        int start_y = y - y % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[start_x + i][start_y + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static Point findEmpty() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
}
