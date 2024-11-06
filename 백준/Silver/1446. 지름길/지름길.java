import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] dist;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        dist = new int[d + 1];
        graph = new ArrayList[d + 1];

        for (int i = 0; i <= d; i++) {
            dist[i] = i;
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (e <= d) {
                graph[s].add(new Node(e, w));
            }
        }

        for (int i = 0; i < d; i++) {

            dist[i + 1] = Math.min(dist[i] + 1, dist[i + 1]);

            for (Node adj : graph[i]) {
                if (adj.to > d) continue;
                dist[adj.to] = Math.min(dist[adj.to], dist[i] + adj.w);
            }
        }

        System.out.println(dist[d]);
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}