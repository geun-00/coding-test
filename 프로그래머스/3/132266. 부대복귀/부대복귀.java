import java.util.*;

class Solution {
    
    int[] dist;
    ArrayList<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        dist = new int[n + 1];
        Arrays.fill(dist , -1);

        bfs(destination);

        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = dist[sources[i]];
        }

        return result;
    }
    
    public void bfs(int start) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);
        dist[start] = 0;

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : graph[now]) {
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    qu.offer(next);
                }
            }
        }
    }
}