import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        if (n == k) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        int[] time = new int[100_001];
        time[n] = 1;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(n);

        int min = Integer.MAX_VALUE;
        int count = 0;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            
            if (time[now] > min) {
                break;
            }

            for (int next : new int[]{now + 1, now - 1, now * 2}) {

                if (0 <= next && next <= 100_000) {

                    if (next == k) {
                        count++;
                        min = time[now];
                    }
                    if (time[next] == 0 || time[next] == time[now] + 1) {
                        qu.offer(next);
                        time[next] = time[now] + 1;
                    }
                }
            }
        }

        System.out.println(min + "\n" + count);
    }
}
