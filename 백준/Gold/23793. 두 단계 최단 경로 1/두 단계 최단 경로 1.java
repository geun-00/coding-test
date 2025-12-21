import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v, w});
        }

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        int z = Integer.parseInt(st.nextToken()) - 1;

        int[] fromXSkip = dijkstra(x, graph, n, true, y);
        int[] fromXNoSkip = dijkstra(x, graph, n, false, -1);
        int[] fromY = dijkstra(y, graph, n, false, -1);

        int xyz = -1;
        if (fromXNoSkip[y] != Integer.MAX_VALUE && fromY[z] != Integer.MAX_VALUE) {
            xyz = fromXNoSkip[y] + fromY[z];
        }

        int xz = -1;
        if (fromXSkip[z] != Integer.MAX_VALUE) {
            xz = fromXSkip[z];
        }

        System.out.println(xyz + " " + xz);
    }

    private static int[] dijkstra(int start, List<int[]>[] graph, int n, boolean skip, int y) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int to = node[0];
            int w = node[1];

            if (w > dist[to]) {
                continue;
            }

            for (int[] next : graph[to]) {
                if (skip && next[0] == y) {
                    continue;
                }
                if (dist[next[0]] > dist[to] + next[1]) {
                    dist[next[0]] = dist[to] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist;
    }
}