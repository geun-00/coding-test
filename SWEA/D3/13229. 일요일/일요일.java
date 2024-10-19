import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("MON", 6);
        map.put("TUE", 5);
        map.put("WED", 4);
        map.put("THU", 3);
        map.put("FRI", 2);
        map.put("SAT", 1);
        map.put("SUN", 7);

        for (int i = 1; i <= t; i++) {
            String s = br.readLine();
            int res = map.get(s);
            System.out.println("#" + i + " " + res);
        }
	}
}