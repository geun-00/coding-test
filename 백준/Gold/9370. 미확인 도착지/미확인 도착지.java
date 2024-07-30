import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); //교차로(노드 수)
            int m = Integer.parseInt(st.nextToken()); //도로(간선 수)
            int t = Integer.parseInt(st.nextToken()); //목적지 개수(도착 노드 수)

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));
            }

            int[] fromS = dijkstra(s, n);
            int[] fromG = dijkstra(g, n);
            int[] fromH = dijkstra(h, n);

            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < t; i++) {

                int destination = Integer.parseInt(br.readLine());

                int dist = fromS[destination];
                int dist1 = fromS[g] + fromG[h] + fromH[destination];
                int dist2 = fromS[h] + fromH[g] + fromG[destination];

                if (dist == dist1 || dist == dist2) {
                    list.add(destination);
                }
            }

            list.sort(null);

            for (int num : list) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[] dijkstra(int start, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        boolean[] visit = new boolean[n + 1];

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.num]) {
                continue;
            }
            visit[now.num] = true;

            for (Node next : graph[now.num]) {

                if (dist[next.num] > dist[now.num] + next.w) {
                    dist[next.num] = dist[now.num] + next.w;
                    qu.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node>{
        int num, w;

        public Node(int num, int w) {
            this.num = num;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
