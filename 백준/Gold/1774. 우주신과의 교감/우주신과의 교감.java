import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];

        double[][] arr = new double[n][2];

        for (int i = 0; i < n; i++) {
            parent[i] = i;

            st = new StringTokenizer(br.readLine());
            arr[i][0] = Double.parseDouble(st.nextToken());
            arr[i][1] = Double.parseDouble(st.nextToken());
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            double x1 = arr[i][0];
            double y1 = arr[i][1];

            for (int j = 0; j < n; j++) {

                double x2 = arr[j][0];
                double y2 = arr[j][1];

                if (i != j) {

                    double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

                    qu.offer(new Edge(i, j, dist));
                    qu.offer(new Edge(j, i, dist));

                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            union(a, b);
        }

        double ans = 0;

        while (!qu.isEmpty()) {

            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                ans += e.w;
            }
        }

        System.out.printf("%.2f", ans);
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

    static class Edge implements Comparable<Edge> {

        int from, to;
        double w;

        public Edge(int from, int to, double w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}