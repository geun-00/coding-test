import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {

            int n = Integer.parseInt(br.readLine());

            ArrayList<Integer> list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                String command = st.nextToken();

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                for (int i = 0; i < y; i++) {
                    int s = Integer.parseInt(st.nextToken());

                    list.add(x + i, s);
                }
            }

            System.out.print("#" + tc + " ");
            for (int i = 0; i < 10; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
	}
}