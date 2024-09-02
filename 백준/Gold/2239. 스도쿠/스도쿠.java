import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

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

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean solve() {
        Point p = findEmpty();
        if (p == null) {
            return true;
        }

        int x = p.x;
        int y = p.y;

        for (int i = 1; i <= 9; i++) {

            if (check(x, y, i)) {
                board[x][y] = i;

                if (solve()) {
                    return true;
                }

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

        int start_x = x - x % 3;
        int start_y = y - y % 3;

        //3x3 사각형 확인
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
