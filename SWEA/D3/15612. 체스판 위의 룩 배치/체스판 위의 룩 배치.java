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

            int[] rook_x = new int[8];
            int[] rook_y = new int[8];

            int count = 0;
            for (int i = 0; i < 8; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 0; j < 8; j++) {
                    if (arr[j] == 'O') {
                        rook_x[i]++;
                        rook_y[j]++;
                        count++;
                    }
                }
            }

            String res = "yes";
            if (count != 8) {
                res = "no";
            }

            for (int i = 0; i < 8; i++) {
                if (rook_x[i] != 1 || rook_y[i] != 1) {
                    res = "no";
                    break;
                }
            }

            sb.append(res);
            
            sb.append("\n");
        }

        System.out.print(sb);
	}
}