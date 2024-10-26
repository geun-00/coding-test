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

            int s = 13, d = 13, h = 13, c = 13;

            String str = br.readLine();

            HashSet<String> set = new HashSet<>();

            boolean error = false;

            for (int j = 0; j <= str.length() - 3; j += 3) {

                String card = str.substring(j, j + 3);

                if (!set.add(card)) {
                    error = true;
                    break;
                }

                switch (card.charAt(0)) {
                    case 'S':
                        s--;
                        break;
                    case 'D':
                        d--;
                        break;
                    case 'H':
                        h--;
                        break;
                    case 'C':
                        c--;
                        break;
                }
            }

            if (error) {
                sb.append("ERROR").append("\n");
            } else {
                sb
                        .append(s)
                        .append(" ")
                        .append(d)
                        .append(" ")
                        .append(h)
                        .append(" ")
                        .append(c)
                        .append(" ")
                        .append("\n");
            }
        }

        System.out.print(sb);
	}
}