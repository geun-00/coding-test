import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        PriorityQueue<Edge> qu = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            qu.offer(new Edge(0, i, Integer.parseInt(br.readLine())));
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                int p = Integer.parseInt(st.nextToken());
                if (i == j) continue;

                qu.offer(new Edge(i, j, p));
            }
        }

        int ans = 0;
        while (!qu.isEmpty()) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                ans += e.cost;
                union(e.from, e.to);
            }
        }
        System.out.println(ans);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
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