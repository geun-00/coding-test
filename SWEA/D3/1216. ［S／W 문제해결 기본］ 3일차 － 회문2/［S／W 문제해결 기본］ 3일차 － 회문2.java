import java.util.*;
import java.io.*;

class Solution
{
    static char[][] board;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int tc = 1; tc <= 10; tc++) {

            int n = Integer.parseInt(br.readLine());

            board = new char[100][100];

            for (int i = 0; i < 100; i++) {
                board[i] = br.readLine().toCharArray();
            }

            int res = 1;
            for (int i = 100; i > 1; i--) {
                if (isPalindrome(i)) {
                    res = i;
                    break;
                }
            }

            System.out.println("#" + n + " " + res);
        }
	}
    
    public static boolean isPalindrome(int len) {

        for (int row = 0; row < 100; row++) {
            for (int col = 0; col <= 100 - len; col++) {
                if (check(row, col, len, true)) {
                    return true;
                }
            }
        }

        for (int col = 0; col < 100; col++) {
            for (int row = 0; row <= 100 - len; row++) {
                if (check(col, row, len, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean check(int row, int col, int len, boolean isRow) {

        int end = col + len - 1;

        while (col < end) {
            if (isRow) {
                if (board[row][col] != board[row][end]) {
                    return false;
                }
            } else {
                if (board[col][row] != board[end][row]) {
                    return false;
                }
            }
            col++;
            end--;
        }

        return true;
    }
}