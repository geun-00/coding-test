import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {

            if (c == 'A' || c == 'D' || c == 'P' || c == 'Q' || c == 'O' || c == 'R') {
                map.put(c, 1);
            } else {
                map.put(c, 0);
            }
        }

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {

            sb.append("#").append(i).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            String a = st.nextToken();
            String b = st.nextToken();

            if (a.length() != b.length()) {
                sb.append("DIFF").append("\n");
                continue;
            }

            boolean same = true;
            for (int j = 0; j < a.length(); j++) {
                char c1 = a.charAt(j);
                char c2 = b.charAt(j);

                if ((c1 == 'B' && c2 != 'B') || (c2 == 'B' && c1 != 'B')) {
                    same = false;
                    break;
                }

                if (map.get(a.charAt(j)) != map.get(b.charAt(j))) {
                    same = false;
                    break;
                }
            }

            sb.append(same ? "SAME" : "DIFF");

            sb.append("\n");
        }

        System.out.print(sb);
	}
}