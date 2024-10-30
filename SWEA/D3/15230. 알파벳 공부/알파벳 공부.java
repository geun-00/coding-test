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

            String s = br.readLine();

            if (s.charAt(0) != 'a') {
                sb.append(0).append("\n");
                continue;
            }

            int count = 0;

            char next = 'a';
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != next) {
                    break;
                }
                next++;
                count++;
            }

            sb.append(count);

            sb.append("\n");
        }

        System.out.print(sb);
	}
}