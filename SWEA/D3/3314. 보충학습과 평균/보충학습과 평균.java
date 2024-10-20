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

            int sum = 0;
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                sum += Math.max(40, num);
            }

            System.out.println("#" + i + " " + (sum / 5));
        }
	}
}