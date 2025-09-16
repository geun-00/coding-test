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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;

        int[] homes = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            homes[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        List<int[]>[] graph = new List[v];
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{x, l});
            graph[x].add(new int[]{u, l});
        }

        int ans = 0;
        for (int h : homes) {
            int[] dist = dijkstra(graph, v, h);
            ans += (dist[a] == Integer.MAX_VALUE ? -1 : dist[a]);
            ans += (dist[b] == Integer.MAX_VALUE ? -1 : dist[b]);
        }

        System.out.println(ans);
    }

    private static int[] dijkstra(List<int[]>[] graph, int v, int start) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int now = cur[0];
            int cost = cur[1];

            if (cost > dist[now]) {
                continue;
            }

            for (int[] next : graph[now]) {
                if (dist[now] + next[1] < dist[next[0]]) {
                    dist[next[0]] = dist[now] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist;
    }
}