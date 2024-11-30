import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        final int INF = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[u].add(new Node(v, c, d));
            }

            int[][] dp = new int[n + 1][m + 1];

            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], INF);
                Collections.sort(graph[i]);
            }

            dp[1][0] = 0;

            PriorityQueue<Node> qu = new PriorityQueue<>();
            qu.offer(new Node(1, 0, 0));

            while (!qu.isEmpty()) {

                Node now = qu.poll();

                if (dp[now.to][now.cost] < now.dist) continue;

                for (Node adj : graph[now.to]) {

                    int nextNode = adj.to;
                    int nextCost = now.cost + adj.cost;
                    int nextDist = now.dist + adj.dist;

                    if (nextCost > m) continue;

                    if (dp[nextNode][nextCost] > nextDist) {
                        dp[nextNode][nextCost] = nextDist;

                        qu.offer(new Node(nextNode, nextCost, nextDist));
                    }
                }
            }

            int ans = INF;
            for (int i = 0; i <= m; i++) {
                ans = Math.min(ans, dp[n][i]);
            }

            sb.append(ans == INF ? "Poor KCM" : ans).append("\n");
        }

        System.out.print(sb);
    }

    static class Node implements Comparable<Node> {

        int to, cost, dist;

        public Node(int to, int cost, int dist) {
            this.to = to;
            this.cost = cost;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
