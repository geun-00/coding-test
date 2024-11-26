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

        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[f + 1];
        visit[s] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(s);

        int ans = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                int now = qu.poll();

                if (now == g) {
                    System.out.println(ans);
                    return;
                }

                if (now + u <= f && !visit[now + u]) {
                    visit[now + u] = true;
                    qu.offer(now + u);
                }

                if (now - d >= 1 && !visit[now - d]) {
                    visit[now - d] = true;
                    qu.offer(now - d);
                }
            }

            ans++;
        }

        System.out.println("use the stairs");
    }
}