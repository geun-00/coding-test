import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean[] arr = new boolean[n + 1];

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < k; j++) {
                arr[Integer.parseInt(st.nextToken())] = true;
            }

            System.out.print("#" + i + " ");

            for (int j = 1; j <= n; j++) {
                if (!arr[j]) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
	}
}