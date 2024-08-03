import java.util.*;

class Solution {
    
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int[] cost : costs) {
            int s = cost[0];
            int e = cost[1];
            int w = cost[2];

            edges.offer(new Edge(s, e, w));
        }
        
        int used = 0;
        int result = 0;

        while (used < n - 1) {
            Edge edge = edges.poll();
            int s = edge.s;
            int e = edge.e;
            int w = edge.w;

            if (find(s) != find(e)) {
                union(s, e);
                result += w;
                used++;
            }
        }

        return result;
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}

class Edge implements Comparable<Edge> {
    int s, e, w;

    public Edge(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}