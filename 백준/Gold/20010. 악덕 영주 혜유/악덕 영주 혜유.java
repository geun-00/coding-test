import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static List<int[]>[] tree;
    static boolean[] visited;
    static int maxDist = 0, maxNode = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        initParent(n);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, c));
            pq.offer(new Edge(b, a, c));
        }

        int cost = 0;
        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int from = cur.from;
            int to = cur.to;

            if (find(from) != find(to)) {
                union(from, to);
                cost += cur.cost;

                tree[from].add(new int[]{to, cur.cost});
                tree[to].add(new int[]{from, cur.cost});
            }
        }

        visited = new boolean[n];
        dfs(0, 0);

        visited = new boolean[n];
        dfs(maxNode, 0);

        System.out.println(cost);
        System.out.println(maxDist);
    }

    private static void dfs(int node, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            maxNode = node;
        }

        visited[node] = true;
        for (int[] next : tree[node]) {
            if (!visited[next[0]]) {
                dfs(next[0], dist + next[1]);
            }
        }
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