class Solution {
    
    //상, 좌, 우, 하
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    
    public int[] solution(String[][] places) {
        
        int[] ans = new int[5];
        char[][] arr = new char[5][5];
        
        for(int i = 0; i < 5; i++) {
            
            String[] place = places[i];            
            
            for(int j = 0; j < 5; j++) {
                arr[j] = place[j].toCharArray();                        
            }
            
            ans[i] = check(arr);            
        }
        
        return ans;
    }
    
    public int check(char[][] arr) {
        
        for(int i = 0; i < 5; i++) {                                    
            for(int j = 0; j < 5; j++) {
                 
                if(arr[i][j] != 'P') continue;
                    
                if(!check(arr, i, j)) return 0;                
            }                  
        }
        
        return 1;
    }
    
    public boolean check(char[][] arr, int x, int y) {
        
        for(int i = 0; i < 4; i++) {
            
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || arr[nx][ny] == 'X') continue;
            
            if(arr[nx][ny] == 'P') return false;
            
            if(check(arr, nx, ny, 3 - i)) return false;
        }
        
        return true;
    }
    
    public boolean check(char[][] arr, int x, int y, int excludeDir) {
        
        for(int i = 0; i < 4; i++) {
            
            if(i == excludeDir) continue;
            
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || arr[nx][ny] == 'X') continue;
            
            if(arr[nx][ny] == 'P') return true;
        }
        
        return false;
    }
}