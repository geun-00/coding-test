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

            int hour_1 = Integer.parseInt(st.nextToken());
            int min_1 = Integer.parseInt(st.nextToken());

            int hour_2 = Integer.parseInt(st.nextToken());
            int min_2 = Integer.parseInt(st.nextToken());

            int result_hour = (hour_1 + hour_2) % 12;
            int result_min = min_1 + min_2;

            result_hour = Math.max(1, result_hour);

            if (result_min >= 60) {
                result_hour++;
                result_min %= 60;
            }

            System.out.println("#" + i + " " + result_hour + " " + result_min);

        }
	}
}