import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {

            sb.append("#").append(i).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            for (int j = 1; j <= q; j++) {
                st = new StringTokenizer(br.readLine());

                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;

                for (int k = l; k <= r; k++) {
                    arr[k] = j;
                }
            }

            for(int num : arr){
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
	}
}