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
        int m = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split(" ");

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if (arr[from].equals(arr[to])) {
                continue;
            }

            qu.offer(new Edge(from, to, cost));
        }

        int ans = 0;
        int link = 0;

        while (!qu.isEmpty()) {

            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                ans += e.cost;
                link++;
            }
        }

        System.out.println(link == n - 1 ? ans : -1);
    }

    public static int find(int a){
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
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
