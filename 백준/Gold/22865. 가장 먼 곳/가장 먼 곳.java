import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        List<int[]>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[d].add(new int[]{e, l});
            graph[e].add(new int[]{d, l});
        }

        int[] distA = dijkstra(graph, a, n);
        int[] distB = dijkstra(graph, b, n);
        int[] distC = dijkstra(graph, c, n);

        int temp = Integer.MIN_VALUE;
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int minDist = Math.min(distA[i], Math.min(distB[i], distC[i]));

            if (minDist > temp) {
                temp = minDist;
                ans = i;
            }
        }

        System.out.println(ans);
    }

    private static int[] dijkstra(List<int[]>[] graph, int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;

        boolean[] visit = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int to = cur[0];

            if (visit[to]) {
                continue;
            }
            visit[to] = true;

            for (int[] next : graph[to]) {
                int newDist = dist[to] + next[1];

                if (newDist < dist[next[0]]) {
                    dist[next[0]] = newDist;
                    pq.offer(new int[]{next[0], newDist});
                }
            }
        }

        return dist;
    }
}