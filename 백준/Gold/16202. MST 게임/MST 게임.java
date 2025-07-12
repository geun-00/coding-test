import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static boolean canMst = true;
    static PriorityQueue<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        edges = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(from, to, i + 1));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            initParent(n);
            sb.append(mst(n)).append(" ");
        }

        System.out.print(sb);
    }

    private static int mst(int n) {
        if (!canMst) {
            return 0;
        }

        int cost = 0;
        int used = 0;

        PriorityQueue<Edge> nextEdges = new PriorityQueue<>(edges);
        Edge removedEdge = null;

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (edge.removed) continue;

            int from = edge.from;
            int to = edge.to;

            if (find(from) != find(to)) {
                if (removedEdge == null) {
                    removedEdge = edge;
                }
                union(from, to);
                cost += edge.cost;
                used++;
            }
        }

        if (used != n - 1) {
            canMst = false;
            return 0;
        }

        if (removedEdge != null) {
            removedEdge.removed = true;
        }

        edges = nextEdges;
        return cost;
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
        boolean removed;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}