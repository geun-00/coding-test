import java.util.*;
import java.io.*;

class Solution
{
    static int[][] arr;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            arr = new int[9][9];

            for (int j = 0; j < 9; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 9; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            boolean flag = true;

            for (int j = 0; j < 9; j += 3) {
                for (int k = 0; k < 9; k += 3) {
                    if (!check_3_3(j, k)) {
                        flag = false;
                        break;
                    }
                }
            }

            if (!flag) {
                printZero(i);
                continue;
            }

            for (int j = 0; j < 9; j++) {
                if (!checkRow(j)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                printZero(i);
                continue;
            }

            for (int j = 0; j < 9; j++) {
                if (!checkCol(j)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                printZero(i);
                continue;
            }

            System.out.println("#" + i + " " + 1);
        }
	}
    
    public static void printZero(int i) {
        System.out.println("#" + i + " " + 0);
    }
    
    public static boolean checkCol(int c) {
        boolean[] used = new boolean[10];

        for (int i = 0; i < 9; i++) {
            if (used[arr[i][c]]) {
                return false;
            }
            used[arr[i][c]] = true;
        }

        return true;
    }

    public static boolean checkRow(int r) {
        boolean[] used = new boolean[10];

        for (int i = 0; i < 9; i++) {
            if (used[arr[r][i]]) {
                return false;
            }
            used[arr[r][i]] = true;
        }

        return true;
    }

    public static boolean check_3_3(int x, int y) {
        boolean[] used = new boolean[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (used[arr[i][j]]) {
                    return false;
                }
                used[arr[i][j]] = true;
            }
        }
        return true;
    }
}