import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int person = 0;
            int maxSolved = 0;

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int count = 0;
                for (int k = 0; k < m; k++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) {
                        count++;
                    }
                }
                if (count > maxSolved) {
                    person = 1;
                    maxSolved = count;
                }
                else if (count >= maxSolved) {
                    person++;
                }
            }

            System.out.println("#" + i + " " + person + " " + maxSolved);
        }
	}
}