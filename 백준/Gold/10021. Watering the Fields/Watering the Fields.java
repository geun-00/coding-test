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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        parent = new int[n];
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        for (int i = 0; i < n - 1; i++) {
            int xi = arr[i][0];
            int yi = arr[i][1];
            for (int j = i + 1; j < n; j++) {
                int xj = arr[j][0];
                int yj = arr[j][1];

                int dist = ((xi - xj) * (xi - xj)) + ((yi - yj) * (yi - yj));

                if (dist >= c) {
                    pq.offer(new Edge(i, j, dist));
                }
            }
        }

        int connected = 0;
        int ans = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                ans += e.w;
                connected++;
            }
        }

        System.out.println(connected == n - 1 ? ans : -1);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}