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

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        st.nextToken();
        st.nextToken();
        int temp = 1 - Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges1 = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);
        PriorityQueue<Edge> edges2 = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges1.offer(new Edge(a, b, c));
            edges2.offer(new Edge(a, b, c));
        }

        int count1 = temp;

        while (!edges1.isEmpty()) {
            Edge e = edges1.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                if (e.w == 0) {
                    count1++;
                }
            }
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int count2 = temp;

        while (!edges2.isEmpty()) {
            Edge e = edges2.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                if (e.w == 0) {
                    count2++;
                }
            }
        }

        System.out.println((count2 * count2) - (count1 * count1));

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
