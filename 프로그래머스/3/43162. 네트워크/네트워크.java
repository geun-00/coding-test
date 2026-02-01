import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        boolean[] visited = new boolean[n];
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, visited, graph);                
                answer++;
            }
        }
        
        return answer;
    }
    
    public void bfs(int node, boolean[] visited, List<Integer>[] graph) {
        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(node);
        
        visited[node] = true;
        
        while (!qu.isEmpty()) {
            int cur = qu.poll();
            
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    qu.offer(next);
                }
            }
        }
    }
}