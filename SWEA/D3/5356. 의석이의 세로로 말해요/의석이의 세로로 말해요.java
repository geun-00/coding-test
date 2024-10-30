import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= t; i++) {

            sb.append("#").append(i).append(" ");

            char[][] arr = new char[5][15];
            for (int j = 0; j < 5; j++) {
                Arrays.fill(arr[j], ' ');
            }
            int n = 0;

            for (int j = 0; j < 5; j++) {
                char[] ch = br.readLine().toCharArray();
                n = Math.max(n, ch.length);

                for (int k = 0; k < ch.length; k++) {
                    arr[j][k] = ch[k];
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 5; k++) {
                    if (arr[k][j] != ' ') {
                        sb.append(arr[k][j]);
                    }
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
	}
}