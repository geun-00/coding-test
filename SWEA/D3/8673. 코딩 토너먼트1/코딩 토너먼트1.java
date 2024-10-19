import java.util.*;
import java.io.*;

class Solution
{
    static int[] arr;
    static int res;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            int n = Integer.parseInt(br.readLine());

            int end = (int) Math.pow(2, n);
            arr = new int[end];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < end; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            res = 0;
            solve(0, end - 1);

            System.out.println("#" + i + " " + res);
        }
	}
    
    private static int solve(int s, int e) {

        if (s >= e) {
            return s;
        }

        if (e - s == 1) {
            if (arr[e] > arr[s]) {
                res += arr[e] - arr[s];
                return e;
            } else {
                res += arr[s] - arr[e];
                return s;
            }
        }

        int mid = (s + e) / 2;
        int left = solve(s, mid);
        int right = solve(mid + 1, e);

        if (arr[right] > arr[left]) {
            res += arr[right] - arr[left];
            return right;
        } else {
            res += arr[left] - arr[right];
            return left;
        }
    }
}