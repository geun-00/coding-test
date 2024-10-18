import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            int n = Integer.parseInt(br.readLine());

            char[][] arr = new char[20][10];
            for (char[] chars : arr) {
                Arrays.fill(chars, ' ');
            }

            int rowIdx = 0;
            int colIdx = 0;

            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                char alpha = st.nextToken().charAt(0);
                int k = Integer.parseInt(st.nextToken());

                while (k-- > 0) {
                    arr[rowIdx][colIdx++] = alpha;
                    if (colIdx > 9) {
                        rowIdx++;
                        colIdx = 0;
                    }
                }
            }

           System.out.println("#" + i);
            for (int j = 0; j <= rowIdx; j++) {
                int temp = (j == rowIdx) ? colIdx : 9;
                for (int k = 0; k <= temp; k++) {
                    System.out.print(arr[j][k]);
                }
                System.out.println();
            }
        }
	}
}