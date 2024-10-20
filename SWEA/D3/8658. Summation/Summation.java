import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            int[] arr = new int[10];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                int num = Integer.parseInt(st.nextToken());

                int sum = 0;
                while (num > 0) {
                    sum += num % 10;
                    num /= 10;
                }
                arr[j] = sum;
            }

            Arrays.sort(arr);

            System.out.println("#" + i + " " + arr[9] + " " + arr[0]);
        }
	}
}