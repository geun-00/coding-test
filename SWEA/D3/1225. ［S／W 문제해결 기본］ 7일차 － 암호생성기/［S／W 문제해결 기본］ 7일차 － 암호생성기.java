import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {

            int n = Integer.parseInt(br.readLine());

            Queue<Integer> qu = new ArrayDeque<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                qu.offer(Integer.parseInt(st.nextToken()));
            }

            int minus = 1;

            while (true) {
                int num = qu.poll();

                int newNum = num - minus;

                if (newNum > 0) {
                    minus++;
                    qu.offer(newNum);
                } else {
                    qu.offer(0);
                    break;
                }

                if (minus > 5) {
                    minus = 1;
                }
            }

            System.out.print("#" + i);
            for (int num : qu) {
                System.out.print(" " + num);
            }
            System.out.println();
        }
	}
}