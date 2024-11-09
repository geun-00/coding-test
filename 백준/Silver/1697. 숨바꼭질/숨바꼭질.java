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
            return;
        }

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(n);

        boolean[] visit = new boolean[100_001];
        visit[n] = true;

        int count = 0;

        while (true) {

            count++;
            
            int size = qu.size();
            for (int i = 0; i < size; i++) {

                int now = qu.poll();

                if (now + 1 == k || now - 1 == k || now * 2 == k) {
                    System.out.println(count);
                    return;
                }

                if (now - 1 >= 0 && !visit[now - 1]) {
                    visit[now - 1] = true;
                    qu.offer(now - 1);
                }

                if (now + 1 <= 100_000 && !visit[now + 1]) {
                    visit[now + 1] = true;
                    qu.offer(now + 1);
                }

                if (now * 2 <= 100_000 && !visit[now * 2]) {
                    visit[now * 2] = true;
                    qu.offer(now * 2);
                }
            }
        }
    }
}