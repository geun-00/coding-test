import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10; i++) {

            sb.append("#").append(i).append(" ");

            ArrayList<Integer> list = new ArrayList<>();

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                String command = st.nextToken();

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if ("I".equals(command)) {
                   for (int j = 0; j < y; j++) {
                        int s = Integer.parseInt(st.nextToken());
                        list.add(x + j, s);
                    }
                }
                else {
                    for (int j = 0; j < y; j++) {
                        list.remove(x);
                    }
                }
            }

            for (int j = 0; j <  Math.min(10, list.size()); j++) {
                sb.append(list.get(j)).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
	}
}