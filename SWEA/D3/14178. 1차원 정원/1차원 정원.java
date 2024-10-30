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
            int d = Integer.parseInt(st.nextToken());

            int range = d * 2 + 1;

            sb.append(n % range == 0 ? n / range : n / range + 1);

            sb.append("\n");
        }

        System.out.print(sb);
	}
}