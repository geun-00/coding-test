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

            char[][] map = new char[n][n];

            boolean first = true;
            int min_x = -1, min_y = -1;
            int max_x = -1, max_y = -1;

            for (int j = 0; j < n; j++) {
                char[] arr = br.readLine().toCharArray();
                for (int k = 0; k < n; k++) {
                    map[j][k] = arr[k];
                    if (map[j][k] == '#') {
                        if (first) {
                            first = false;
                            min_x = j;
                            min_y = k;
                        }
                        max_x = j;
                        max_y = k;
                    }
                }
            }

            if ((max_x - min_x) != (max_y - min_y)) {
                sb.append("no").append("\n");
                continue;
            }
            
            String ans = "yes";

            loop:
            for (int j = min_x; j <= max_x; j++) {
                for (int k = min_y; k <= max_y; k++) {
                    if (map[j][k] != '#') {
                        ans = "no";
                        break loop;
                    }
                }
            }

            sb.append(ans);

            sb.append("\n");
        }
        System.out.print(sb);
	}
}