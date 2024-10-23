import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] times = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(times);

            String res = "Possible";

            for (int i = 0; i < n; i++) { 
                int temp = times[i] / m * k;

                if (temp < i + 1) {
                    res = "Impossible";
                    break;
                }
            }

            System.out.println("#" + tc + " " + res);
        }
	}
}