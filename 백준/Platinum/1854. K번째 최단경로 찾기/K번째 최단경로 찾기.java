import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 인접 리스트, 우선순위 큐에 저장할 노드
    static class Node implements Comparable<Node>{
        int node, cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] distance = new int[n + 1][k];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        ArrayList<Node>[] A = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            A[a].add(new Node(b, c));
        }

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        distance[1][0] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int node = now.node;
            int cost = now.cost;

            for (Node next : A[node]) {
                int nextNode = next.node;
                int nextCost = next.cost;

                int newCost = cost + nextCost;

                if (distance[nextNode][k - 1] > newCost) {
                    distance[nextNode][k - 1] = newCost;

                    Arrays.sort(distance[nextNode]); // 중요!
                    pq.add(new Node(nextNode, newCost));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            if (distance[i][k - 1] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(distance[i][k - 1]).append("\n");
            }
        }
        System.out.println(sb);
    }
}