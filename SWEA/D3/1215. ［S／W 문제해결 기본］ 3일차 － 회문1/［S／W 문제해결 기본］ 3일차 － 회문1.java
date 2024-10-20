import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {

            int n = Integer.parseInt(br.readLine());

            char[][] board = new char[8][8];

            for (int j = 0; j < 8; j++) {
                board[j] = br.readLine().toCharArray();
            }

            if (n == 1) {
                System.out.println("#" + i + " " + 64);
                continue;
            }

            int endRow = 8 - n + 1;
            int endCol = 8 - n + 1;

            int count = 0;

            for (int j = 0; j < endRow; j++) {
                for (int k = 0; k < 8; k++) {
                    String s = "";
                    for (int l = j; l < j + n; l++) {
                        s += board[l][k];
                    }
                    count += check(s);
                }
            }

            for (int j = 0; j < endCol; j++) {
                for (int k = 0; k < 8; k++) {
                    String s = "";
                    for (int l = j; l < j + n; l++) {
                        s += board[k][l];
                    }
                    count += check(s);
                }
            }


            System.out.println("#" + i + " " + count);
        }
	}
    
    public static int check(String str) {

        int s = 0, e = str.length() - 1;

        while (s < e) {
            if (str.charAt(s) != str.charAt(e)) {
                return 0;
            }
            s++;
            e--;
        }

        return 1;
    }
}