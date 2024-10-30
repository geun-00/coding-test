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

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] fee = new int[n + 1];
            PriorityQueue<Integer> empty = new PriorityQueue<>();

            //주차 공간의 단위 무게당 요금
            for (int j = 1; j <= n; j++) {
                int r = Integer.parseInt(br.readLine());
                fee[j] = r;
                empty.offer(j);
            }

            int[] car = new int[m + 1];

            //차량의 무게
            for (int j = 1; j <= m; j++) {
                int w = Integer.parseInt(br.readLine());
                car[j] = w;
            }

            int possible = 1;
            int total = 0;
            int[] parking = new int[n + 1];

            Queue<Integer> ready = new ArrayDeque<>();

            //주차장 출입 순서
            for (int j = 0; j < 2 * m; j++) {
                int x = Integer.parseInt(br.readLine());

                if (x > 0) {
                    if (empty.isEmpty()) {
                        ready.offer(x);
                        continue;
                    }
                    int e = empty.poll();
                    parking[e] = x;
                    total += fee[e] * car[x];
                }
                else {
                    x = Math.abs(x);
                    for (int k = 1; k <= n; k++) {
                        if (parking[k] == x) {
                            parking[k] = 0;
                            empty.offer(k);
                            break;
                        }
                    }
                    if (!ready.isEmpty() && !empty.isEmpty()) {
                        int c = ready.poll();
                        int e = empty.poll();
                        parking[e] = c;
                        total += fee[e] * car[c];
                    }
                }
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb);
	}
}