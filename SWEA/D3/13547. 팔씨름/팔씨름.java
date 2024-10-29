import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {

            sb.append("#").append(tc).append(" ");

            String s = br.readLine();

            int win = 0;

            for (char ch : s.toCharArray()) {
                if (ch == 'o') win++;
            }

            String res = "NO";
            if (15 - s.length() + win >= 8) {
                res = "YES";
            }
            
            sb.append(res);

            sb.append("\n");
        }

        System.out.print(sb);
	}
}