import java.util.*;
import java.io.*;

class Solution
{
    static int count;
    static int[] col;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            count = 0;

            int n = Integer.parseInt(br.readLine());

            col = new int[n];
            dfs(0, n);

            System.out.println("#" + tc + " " + count);
        }
	}
    
    public static void dfs(int depth, int n) {

        if (depth == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            col[depth] = i;
            if (check(depth)) {
                dfs(depth + 1, n);
            }
        }
    }
    
    public static boolean check(int depth) {
        for (int i = 0; i < depth; i++) {
            if (col[depth] == col[i] || Math.abs(col[depth] - col[i]) == Math.abs(depth - i)) {
                return false;
            }
        }

        return true;
    }
}