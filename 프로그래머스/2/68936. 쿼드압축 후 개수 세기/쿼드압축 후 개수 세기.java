class Solution {
    
    static int[][] arr;
    static int zero;
    static int one;
    
    public int[] solution(int[][] input) {
        
        arr = input;
        zero = 0;
        one = 0;
        
        solve(0, 0, input.length);
            
        return new int[]{zero, one};
    }
    
    public void solve(int x, int y, int size) {
        
        if(size == 1) {
            if(arr[x][y] == 0) zero++;
            else if(arr[x][y] == 1) one++;
            
            return;
        }
        
        int half = size / 2;
        
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                
                if(arr[i][j] != arr[x][y]) {
                    solve(x, y, half);
                    solve(x + half, y, half);
                    solve(x, y + half, half);
                    solve(x + half, y + half, half);
                    return;
                }
            }
        }
        
        if(arr[x][y] == 0) zero++;
        else one++;
    }
}