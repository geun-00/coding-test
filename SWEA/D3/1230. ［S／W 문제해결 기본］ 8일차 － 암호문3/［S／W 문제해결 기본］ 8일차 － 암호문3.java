import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {

            int n = Integer.parseInt(br.readLine());

            ArrayList<Integer> list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                String command = st.nextToken();

                int x, y;

                if ("I".equals(command)) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        list.add(x + j, Integer.parseInt(st.nextToken()));
                    }
                } else if ("D".equals(command)) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        list.remove(x + j);
                    }
                } else {
                    y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                }
            }

            System.out.print("#" + i);

            for (int j = 0; j < 10; j++) {
                System.out.print(" " + list.get(j));
            }
            System.out.println();
        }
	}
}