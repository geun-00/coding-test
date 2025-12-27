import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        initParent(n);
        graph = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node[2]));

        int c = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                int cost = Integer.parseInt(st.nextToken());

                if (i < j) {
                    if (cost < 0) {
                        union(i, j);
                        c -= cost;
                    }
                    if (cost > 0) {
                        pq.offer(new int[]{i, j, cost});
                    }
                }
            }
        }

        List<int[]> m = new ArrayList<>();

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int from = node[0];
            int to = node[1];
            int w = node[2];

            if (find(from) != find(to)) {
                union(from, to);
                c += w;
                m.add(new int[]{from, to});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(c).append(" ").append(m.size()).append("\n");

        for (int[] edges : m) {
            sb.append(edges[0]).append(" ").append(edges[1]).append("\n");
        }

        System.out.println(sb);
    }

    private static void initParent(int n) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}