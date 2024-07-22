import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] graph = new ArrayList[n + 1];
            int[] dist = new int[n + 1];
            boolean[] visit = new boolean[n + 1];

            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
                dist[j] = Integer.MAX_VALUE;
            }

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));
            }

            dist[c] = 0;

            PriorityQueue<Node> qu = new PriorityQueue<>();
            qu.offer(new Node(c, 0));


            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (visit[now.adj]) {
                    continue;
                }
                visit[now.adj] = true;

                for (Node next : graph[now.adj]) {
                    if (dist[next.adj] > dist[now.adj] + next.w) {
                        dist[next.adj] = dist[now.adj] + next.w;

                        qu.offer(new Node(next.adj, dist[next.adj]));
                    }
                }
            }

            int max = 0;
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (visit[j]) {
                    count++;
                    max = Math.max(max, dist[j]);
                }
            }

            sb.append(count).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }

    static class Node implements Comparable<Node> {
        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
