import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		String[] num = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<Integer, String> map2 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map1.put(num[i], i);
            map2.put(i, num[i]);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            String len = st.nextToken();

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[Integer.parseInt(len)];
            int idx = 0;
            while (st.hasMoreTokens()) {
                arr[idx++] = map1.get(st.nextToken());
            }

            Arrays.sort(arr);
            StringBuilder res = new StringBuilder();
            for (int n : arr) {
                res.append(map2.get(n)).append(" ");
            }

            System.out.println("#" + tc + " " + res);
        }
	}
}