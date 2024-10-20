import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            int m = Integer.parseInt(br.readLine());

            int[] result = new int[8];

            for (int j = 0; j < 8; j++) {
				 result[j] = m / money[j];
                 m %= money[j];
            }

            System.out.println("#" + i);
            for (int num : result) {
                System.out.print(num + " ");
            }
            System.out.println();

        }
	}
}