import java.util.ArrayList;

class Solution {
    
    ArrayList<Integer>[] graph;
    boolean[] visit;
    
    public int solution(int n, int[][] computers) {
        
        graph = new ArrayList[n];
        visit = new boolean[n];
        
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    if(computers[i][j] == 1){
                        graph[i].add(j);
                        graph[j].add(i);
                    }
                }
            }
        }
        
        int count = 0;
        
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                count++;
                dfs(i);
            }
        }
        
        return count;
    }
    
    public void dfs(int node){
        visit[node] = true;
        
        for(int next : graph[node]){
            if(!visit[next]){
                dfs(next);
            }
        }
    }
}