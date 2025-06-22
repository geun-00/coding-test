import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] dist = new int[n + 1];
        Queue<Integer> qu = new ArrayDeque<>();
        boolean[] visit = new boolean[n + 1];

        qu.offer(1);
        visit[1] = true;

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : graph[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    dist[next] = dist[now] + 1;
                    qu.offer(next);
                }
            }
        }

        int max = Arrays.stream(dist).max().orElse(0);
        int num = IntStream.range(1, n + 1).filter(i -> dist[i] == max).findFirst().orElse(0);
        long count = Arrays.stream(dist).filter(i -> i == max).count();

        System.out.println(num + " " + max + " " + count);
    }
}
