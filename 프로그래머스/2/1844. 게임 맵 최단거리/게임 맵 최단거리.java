import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] visit = new boolean[n][m];
        
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0, 1));
        visit[0][0] = true;
        
        while(!qu.isEmpty()){
            Point now = qu.poll();
            
            if (now.x == n - 1 && now.y == m - 1) {
                return now.count;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && maps[nx][ny] == 1){
                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny, now.count + 1));
                }
            }
        }
        
        return -1;
    }
}

class Point {
    int x, y, count;
    
    public Point(int x, int y, int count){
        this.x = x;
        this.y = y;
        this.count = count;
    }
}