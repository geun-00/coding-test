import java.util.*;

class Solution {
    
    ArrayList<Node>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
       graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] fare : fares) {

            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            graph[c].add(new Node(d, f));
            graph[d].add(new Node(c, f));
        }

        int[] fromS = dijkstra(s, n);
        int[] fromA = dijkstra(a, n);
        int[] fromB = dijkstra(b, n);

        int min = fromS[a] + fromS[b];

        for (int i = 1; i <= n; i++) {
            min = Math.min(min, fromS[i] + fromA[i] + fromB[i]);
        }

        return min;
    }
    
    public int[] dijkstra(int start, int n) {
        
        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Arrays.fill(dist, 201 * 100_000);
        dist[start] = 0;

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;

            for (Node next : graph[now.to]) {
                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;

                    qu.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }
}

class Node implements Comparable<Node>{

    int to, weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}