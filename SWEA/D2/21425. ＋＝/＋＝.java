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
            int n = Integer.parseInt(st.nextToken());

            int count = 0;
            while (a <= n && b <= n) {
                if (a > b) {
                    b += a;
                } else {
                    a += b;
                }
                count++;
            }
            System.out.println(count);
        }
	}
}