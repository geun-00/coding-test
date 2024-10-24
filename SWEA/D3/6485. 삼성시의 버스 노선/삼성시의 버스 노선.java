import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[5001];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                for (int j = a; j <= b; j++) {
                    arr[j]++;
                }
            }

            StringBuilder res = new StringBuilder();
            res.append("#").append(tc).append(" ");

            int p = Integer.parseInt(br.readLine());
            for (int i = 0; i < p; i++) {
                int c = Integer.parseInt(br.readLine());
                res.append(arr[c]).append(" ");
            }
            System.out.println(res);
        }
	}
}