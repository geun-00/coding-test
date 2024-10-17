import java.util.*;
import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] arr = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        int t = Integer.parseInt(br.readLine());

        for(int i = 1; i <= t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int month1 = Integer.parseInt(st.nextToken());
            int day1 = Integer.parseInt(st.nextToken());
            int month2 = Integer.parseInt(st.nextToken());
            int day2 = Integer.parseInt(st.nextToken());

            int between = (arr[month2 - 1] + day2) - (arr[month1 - 1] + day1) + 1;

            System.out.printf("#%d %d\n", i, between);

        }
	}
}