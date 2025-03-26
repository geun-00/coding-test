import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] in = new int[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            in[b]++;
            graph[a].add(b);
        }

        Queue<Integer> qu = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                qu.offer(i);
                sb.append(i).append(" ");
            }
        }

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : graph[now]) {
                if (--in[next] == 0) {
                    sb.append(next).append(" ");
                    qu.offer(next);
                }
            }
        }

        System.out.print(sb);
    }
}
