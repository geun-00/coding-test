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

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[100_001];
        visit[n] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{n, 0});

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();

            int pos = cur[0];
            int count = cur[1];

            if (pos == m) {
                System.out.println(count);
                return;
            }

            if (pos + 1 <= 100_000 && !visit[pos + 1]) {
                qu.offer(new int[]{pos + 1, count + 1});
                visit[pos + 1] = true;
            }
            if (pos - 1 >= 0 && !visit[pos - 1]) {
                qu.offer(new int[]{pos - 1, count + 1});
                visit[pos - 1] = true;
            }
            if (pos + a <= 100_000 && !visit[pos + a]) {
                qu.offer(new int[]{pos + a, count + 1});
                visit[pos + a] = true;
            }
            if (pos - a >= 0 && !visit[pos - a]) {
                qu.offer(new int[]{pos - a, count + 1});
                visit[pos - a] = true;
            }
            if (pos + b <= 100_000 && !visit[pos + b]) {
                qu.offer(new int[]{pos + b, count + 1});
                visit[pos + b] = true;
            }
            if (pos - b >= 0 && !visit[pos - b]) {
                qu.offer(new int[]{pos - b, count + 1});
                visit[pos - b] = true;
            }
            if (pos * a <= 100_000 && !visit[pos * a]) {
                qu.offer(new int[]{pos * a, count + 1});
                visit[pos * a] = true;
            }
            if (pos * b <= 100_000 && !visit[pos * b]) {
                qu.offer(new int[]{pos * b, count + 1});
                visit[pos * b] = true;
            }
        }
    }
}