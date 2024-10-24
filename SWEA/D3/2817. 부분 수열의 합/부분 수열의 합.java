import java.util.*;
import java.io.*;

class Solution
{
    static int count;
    static int n, k;
    static int[] pick;
    static int[] arr;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if (arr[i] == k) {
                    count++;
                }
            }

            for (int i = 2; i <= n; i++) {
                pick = new int[i];
                solve(0, 0, i);
            }

            System.out.println("#" + tc + " " + count);
        }
    }
	
    public static void solve(int index, int start, int limit) {
        if (index == limit) {
            int sum = 0;
            for (int idx : pick) {
                sum += arr[idx];
            }
            if(sum == k) count++;
            return;
        }

        for (int i = start; i < n; i++) {
            pick[index] = i;
            solve(index + 1, i + 1, limit);
        }
    }
}