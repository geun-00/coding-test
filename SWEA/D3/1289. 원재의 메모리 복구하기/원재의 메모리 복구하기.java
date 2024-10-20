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

            char prev = '0';
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c != prev) {
                    prev = c;
                    count++;
                }
            }

            System.out.println("#" + i + " " + count);
        }
	}
}