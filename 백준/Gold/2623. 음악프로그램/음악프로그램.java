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
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());

            for (int j = 0; j < s - 1; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[prev].add(num);
                in[num]++;
                prev = num;
            }
        }

        Queue<Integer> qu = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                qu.offer(i);
            }
        }

        if (qu.isEmpty()) {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();

        while (!qu.isEmpty()) {
            int now = qu.poll();
            sb.append(now).append("\n");

            for (int next : graph[now]) {
                if (--in[next] == 0) {
                    qu.offer(next);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (in[i] > 0) {
                System.out.println(0);
                return;
            }
        }

        System.out.print(sb);
    }
}