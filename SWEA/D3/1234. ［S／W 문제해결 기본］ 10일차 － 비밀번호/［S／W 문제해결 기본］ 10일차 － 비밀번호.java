import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            String s = st.nextToken();

            while (true) {

                boolean flag = false;

                for (int i = 0; i < s.length() - 1; i++) {
                    if (s.charAt(i) == s.charAt(i + 1)) {

                        s = s.substring(0, i) + s.substring(i + 2);
                        flag = true;
                    }
                }

                if (!flag) {
                    break;
                }
            }

            System.out.println("#" + tc + " " + s);
        }
	}
}