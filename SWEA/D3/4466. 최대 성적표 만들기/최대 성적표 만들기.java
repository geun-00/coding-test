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
            int k = Integer.parseInt(st.nextToken());

            int[] score = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                score[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(score);

            int sum = 0;
            for (int j = n - 1; j >= n - k; j--) {
                sum += score[j];
            }
            sb.append(sum);

            sb.append("\n");
        }

        System.out.print(sb);
	}
}