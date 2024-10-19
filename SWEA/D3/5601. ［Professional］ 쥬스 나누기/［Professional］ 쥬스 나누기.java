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

            System.out.print("#" + i + " ");

            for (int j = 0; j < n; j++) {
                System.out.print(1 + "/" + n + " ");
            }
            System.out.println();
        }
	}
}