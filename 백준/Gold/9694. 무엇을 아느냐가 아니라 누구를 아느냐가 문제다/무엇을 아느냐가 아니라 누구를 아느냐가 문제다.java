import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            sb.append("Case #").append(tc).append(": ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Node>[] graph = new List[m];
            for (int i = 0; i < m; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph[x].add(new Node(y, z));
                graph[y].add(new Node(x, z));
            }

            int[] path = dijkstra(graph, m);

            if (path[m - 1] == -1) {
                sb.append(-1);
            } else {
                Deque<Integer> stack = new ArrayDeque<>();
                int node = m - 1;

                while (path[node] != -1) {
                    stack.push(node);
                    node = path[node];
                }

                stack.push(0);

                while (!stack.isEmpty()) {
                    sb.append(stack.pop()).append(" ");
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[] dijkstra(List<Node>[] graph, int m) {
        int[] dist = new int[m];
        int[] path = new int[m];

        Arrays.fill(dist, INF);
        Arrays.fill(path, -1);

        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        pq.offer(new Node(0, 0));

        boolean[] checked = new boolean[m];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (checked[cur.to]) {
                continue;
            }

            checked[cur.to] = true;

            for (Node next : graph[cur.to]) {
                int nextW = dist[cur.to] + next.w;

                if (dist[next.to] > nextW) {
                    dist[next.to] = nextW;
                    pq.offer(new Node(next.to, nextW));
                    path[next.to] = cur.to;
                }
            }
        }

        return path;
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}