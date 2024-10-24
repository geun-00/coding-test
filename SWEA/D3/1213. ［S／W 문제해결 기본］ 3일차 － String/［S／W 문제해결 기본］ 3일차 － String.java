import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       for (int tc = 1; tc <= 10; tc++) {

            int n = Integer.parseInt(br.readLine());

            String target = br.readLine();
            String s = br.readLine();

            String s1 = s.replaceAll(target, "#");

            int res = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == '#') {
                    res++;
                }
            }

            System.out.println("#" + tc + " " + res);
        }
	}
}