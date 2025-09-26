import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        initParent(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                int cost = Integer.parseInt(st.nextToken());

                if (i != 0 && i < j) {
                    pq.offer(new Edge(i + 1, j + 1, cost));
                }
            }
        }

        int sum = 0;
        int count = 0;
        List<int[]> list = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            int from = cur.from;

            if (find(from) != find(to)) {
                sum += cur.cost;
                count++;
                union(from, to);
                list.add(new int[]{from, to});
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(sum).append(" ").append(count);
        for (int[] row : list) {
            sb.append("\n").append(row[0]).append(" ").append(row[1]);
        }

        System.out.print(sb);
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

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}