import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n];
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planets[i] = new Planet(i, x, y, z);
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 0; i < 3; i++) {

            final int t = i;

            Arrays.sort(planets, (a, b) -> {
                if (t == 0) return a.x - b.x;
                else if (t == 1) return a.y - b.y;
                else return a.z - b.z;
            });

            for (int j = 0; j < n - 1; j++) {
                Planet p1 = planets[j];
                Planet p2 = planets[j + 1];

                int cost = Math.min(
                        Math.abs(p1.x - p2.x),
                        Math.min(
                                Math.abs(p1.y - p2.y),
                                Math.abs(p1.z - p2.z)
                        )
                );

                qu.offer(new Edge(p1.id, p2.id, cost));
            }
        }

        int used = 0;
        int cost = 0;

        while (!qu.isEmpty()) {

            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                cost += e.w;
                used++;

                if (used == n - 1) {
                    break;
                }
            }
        }

        System.out.println(cost);
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

        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    static class Planet {

        int id, x, y, z;

        public Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}