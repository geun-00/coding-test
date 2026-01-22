import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Integer>[] graph = new List[n];
        int[] dist = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = -1;
        }
        
        for (int[] e : edge) {
            int a = e[0] - 1;
            int b = e[1] - 1;
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int max = 0;
        
        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(0);
        dist[0] = 0;
        
        while (!qu.isEmpty()) {
            int node = qu.poll();
            
            for (int next : graph[node]) {
                if (dist[next] == -1) {
                    dist[next] = dist[node] + 1;
                    qu.offer(next);
                    max = Math.max(max, dist[next]);
                }
            }
        }
        
        for (int d : dist) {
            if (d == max) answer++;
        }
        
        return answer;
    }
}