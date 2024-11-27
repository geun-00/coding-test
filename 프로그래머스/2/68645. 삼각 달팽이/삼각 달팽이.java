class Solution {
    public int[] solution(int n) {
        
        int[] ans = new int[n * (n + 1) / 2];
        
        int[][] arr = new int[n][n];
        
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        
        int num = 1;
        int x = 0;
        int y = 0;
        int dir = 0;
        
        while(true) {
            
            arr[x][y] = num++;
            
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] != 0) {
                
                dir = (dir + 1) % 3;
                nx = x + dx[dir];
                ny = y + dy[dir];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] != 0) {
                    break;
                }
            }
            
            x = nx;
            y = ny;
        }
        
        int idx = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                ans[idx++] = arr[i][j];
            }
        }
        
        return ans;
    }
}