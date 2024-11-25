import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        PriorityQueue<Long> qu = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            qu.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {

            long num1 = qu.poll();
            long num2 = qu.poll();

            for (int j = 0; j < 2; j++) {
                qu.offer(num1 + num2);
            }
        }

        long ans = 0;

        while (!qu.isEmpty()) {
            ans += qu.poll();
        }

        System.out.println(ans);
    }
}