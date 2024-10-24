import java.util.*;
import java.io.*;

class Solution
{
    static int max;
    
	public static void main(String args[]) throws Exception
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            max = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(arr, l, 0, 0, 0);

            System.out.println("#" + tc + " " + max);
        }
	}
    
    public static void dfs(int[][] arr, int l, int index, int sum, int kcal) {
        if (kcal > l) {
            return;
        }

        if (index > arr.length - 1) {
            max = Math.max(max, sum);
            return;
        }

        dfs(arr, l, index + 1, sum, kcal);
        dfs(arr, l, index + 1, sum + arr[index][0], kcal + arr[index][1]);
    }
}