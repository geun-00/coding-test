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

            int count = 0;

            for (int x = -n; x <= n; x++) {
                for (int y = -n; y <= n; y++) {
                    if (x * x + y * y <= n * n) {
                        count++;
                    }
                }
            }
            System.out.println("#" + i + " " + count);
        }
	}
}