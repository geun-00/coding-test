class Solution {
    public int solution(String[][] board, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int n = board.length;
        int ans = 0;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (board[x][y].equals(board[nx][ny])) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}