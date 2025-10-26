import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int count = 0;
        while (t-- > 0) {
            if (pq.peek() < h) {
                System.out.println("YES");
                System.out.println(count);
                return;
            }

            count++;
            int max = pq.poll();
            if (max == 1) {
                pq.offer(max);
                break;
            }
            pq.offer(max / 2);
        }

        if (pq.peek() < h) {
            System.out.println("YES");
            System.out.println(count);
            return;
        }

        System.out.println("NO");
        System.out.println(pq.peek());
    }
}