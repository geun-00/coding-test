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

            int[] arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int max = -1;
            for (int j = 0; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (check(arr[j] * arr[k])) {
                        max = Math.max(max, arr[j] * arr[k]);
                    }
                }
            }

            sb.append(max).append("\n");
        }

        System.out.print(sb);
	}
    
    public static boolean check(int num) {

        String s = String.valueOf(num);
        if (s.length() == 1) {
            return true;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) - '0' < s.charAt(i - 1) - '0') {
                return false;
            }
        }

        return true;
    }
}