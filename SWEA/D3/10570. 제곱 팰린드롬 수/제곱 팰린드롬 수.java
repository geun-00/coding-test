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

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int count = 0;
            for (int j = a; j <= b; j++) {
                if (j == 1 || j == 4 || j == 9 || j == 121 || j == 484) {
                    count++;
                }
            }

            System.out.println("#" + i + " " + count);
        }
	}
}