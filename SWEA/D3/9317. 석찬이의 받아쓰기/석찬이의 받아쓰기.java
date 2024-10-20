import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            int n = Integer.parseInt(br.readLine());

            String a = br.readLine();
            String b = br.readLine();

            int count = 0;
            for (int j = 0; j < n; j++) {
                char c1 = a.charAt(j);
                char c2 = b.charAt(j);

                if ((c1 - 'a') == (c2 - 'a') || (c1 - 'A') == (c2 - 'A')) {
                    count++;
                }
            }

            System.out.println("#" + i + " " + count);
        }
	}
}