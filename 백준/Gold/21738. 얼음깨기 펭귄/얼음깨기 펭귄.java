import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(p);

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[p] = 0;

        while (!qu.isEmpty()) {
            int cur = qu.poll();

            for (int next : graph[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    qu.offer(next);
                }
            }
        }

        int[] stand = new int[s + 1];
        System.arraycopy(dist, 1, stand, 1, s);

        Arrays.sort(stand);

        System.out.println(n - (stand[1] + stand[2] + 1));
    }
}