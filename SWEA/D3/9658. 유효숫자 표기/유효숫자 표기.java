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

            String n = br.readLine();

            int ex = n.length() - 1;

            int a = n.charAt(0) - '0';
            int b = n.charAt(1) - '0';
            int c = n.charAt(2) - '0';

            if (c >= 5) b++;
            if (b == 10) {
                a++;
                b = 0;
            }
            if (a == 10) {
                a = 1;
                ex++;
            }

            sb
                    .append(a)
                    .append(".")
                    .append(b)
                    .append("*10^")
                    .append(ex)
                    .append("\n");
        }

        System.out.println(sb);
	}
}