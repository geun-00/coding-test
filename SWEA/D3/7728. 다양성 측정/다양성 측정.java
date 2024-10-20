import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            int x = Integer.parseInt(br.readLine());

            HashSet<Integer> set = new HashSet<>();

            while (x > 0) {
                set.add(x % 10);
                x /= 10;
            }

            System.out.println("#" + i + " " + set.size());
        }
	}
}