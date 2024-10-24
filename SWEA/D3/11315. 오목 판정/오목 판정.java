import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringBuilder res = new StringBuilder();
            res.append("#").append(tc).append(" ");

            int n = Integer.parseInt(br.readLine());

            char[][] board = new char[n][n];

            for (int i = 0; i < n; i++) {
                board[i] = br.readLine().toCharArray();
            }

            String result = "NO";

            for (int i = 0; i < n; i++) {
                int count = 0;
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'o') {
                        count++;
                        if (count == 5) {
                            result = "YES";
                            break;
                        }
                    } else {
                        count = 0;
                    }
                }
                if (result.equals("YES")) {
                    break;
                }
            }

            if (result.equals("YES")) {
                res.append(result);
                System.out.println(res);
                continue;
            }

            for (int i = 0; i < n; i++) {
                int count = 0;
                for (int j = 0; j < n; j++) {
                    if (board[j][i] == 'o') {
                        count++;
                        if (count == 5) {
                            result = "YES";
                            break;
                        }
                    } else {
                        count = 0;
                    }
                }
                if (result.equals("YES")) {
                    break;
                }
            }

            if (result.equals("YES")) {
                res.append(result);
                System.out.println(res);
                continue;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    int count = 0;

                    int x = i;
                    int y = j;

                    while (x < n && y < n) {
                        if (board[x][y] == 'o') {
                            count++;
                            if (count == 5) {
                                result = "YES";
                                break;
                            }
                        } else {
                            count = 0;
                        }
                        x++;
                        y++;
                    }
                    if (result.equals("YES")) {
                        break;
                    }

                    x = i;
                    y = j;
                    count = 0;

                    while (x < n  && y >= 0) {
                        if (board[x][y] == 'o') {
                            count++;
                            if (count == 5) {
                                result = "YES";
                                break;
                            }
                        } else {
                            count = 0;
                        }
                        x++;
                        y--;
                    }
                    if (result.equals("YES")) {
                        break;
                    }
                }
                if (result.equals("YES")) {
                    break;
                }
            }

            res.append(result);

            System.out.println(res);
        }
	}
}