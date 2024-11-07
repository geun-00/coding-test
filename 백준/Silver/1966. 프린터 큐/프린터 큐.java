import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            //{index, priority}
            Queue<int[]> order = new ArrayDeque<>();
            PriorityQueue<Integer> priority = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());

                priority.offer(p);
                order.offer(new int[]{i, p});
            }

            int count = 0;

            while (!order.isEmpty()) {
                int[] now = order.poll();

                if (now[1] != priority.peek()) {
                    order.offer(now);
                } else {
                    priority.poll();

                    count++;
                    if (now[0] == m) {
                        sb.append(count).append("\n");
                        break;
                    }
                }
            }

        }

        System.out.print(sb);
    }
}
