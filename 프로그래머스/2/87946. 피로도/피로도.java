class Solution {
    
    boolean[] visit;
    int max = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        visit = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        
        return max;
    }
    
    public void dfs(int depth, int k, int[][] dungeons) {
        max = Math.max(max, depth);
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visit[i] && dungeons[i][0] <= k){
                visit[i] = true;
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                visit[i] = false;
            }
        }
    }
}