class Solution {
    public int solution(String dirs) {
        
        boolean[][][][] visit = new boolean[11][11][11][11];
        
        int x = 5, y = 5;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        int ans = 0;
        
        for(char c : dirs.toCharArray()){
            
            int dir = getDir(c);
            
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx < 0 || ny < 0 || nx > 10 || ny > 10) continue;               
            
            if(!visit[x][y][nx][ny]){
                ans++;
                
                visit[x][y][nx][ny] = true;
                visit[nx][ny][x][y] = true;
            } 
            
            x = nx;
            y = ny;
                    
        }
        
        return ans;
    }
    
    public int getDir(char c){
        
        switch(c) {
                
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'L':
                return 2;
            case 'R':
                return 3;
        }
        
        return -1;
    }
}