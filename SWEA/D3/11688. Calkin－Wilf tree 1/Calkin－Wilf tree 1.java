import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            String s = br.readLine();

            int a = 1, b = 1;
            for (char c : s.toCharArray()) {

                if (c == 'L') {
                    b += a;
                } else {
                    a += b;
                }
            }

            System.out.println("#" + i + " " + a + " " + b);

        }
	}
}