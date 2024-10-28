import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {

            sb.append("#").append(i).append(" ");

            int n = Integer.parseInt(br.readLine());

            sb.append((n % 2 == 0) ? "Alice" : "Bob");

            sb.append("\n");
        }

        System.out.print(sb);
	}
}