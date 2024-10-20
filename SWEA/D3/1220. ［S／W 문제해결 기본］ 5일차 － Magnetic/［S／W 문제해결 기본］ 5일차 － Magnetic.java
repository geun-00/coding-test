import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[100][100];

            for (int j = 0; j < 100; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 100; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;

            for (int j = 0; j < 100; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < 100; k++) {
                    if (arr[k][j] != 0) {
                        sb.append(arr[k][j]);
                    }
                }
                String ns = sb.toString();
                for (int k = 0; k < ns.length() - 1; k++) {
                    if (ns.charAt(k) == '1' && ns.charAt(k + 1) == '2') {
                        count++;
                    }
                }
            }
            System.out.println("#" + i + " " + count);
        }
	}
}