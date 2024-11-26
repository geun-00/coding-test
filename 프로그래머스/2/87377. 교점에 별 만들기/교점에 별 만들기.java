import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        
        ArrayList<Point> points = new ArrayList<>();
        
        int n = line.length;
        
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;
        
        for(int i = 0; i < n - 1; i++) {
            
            int a = line[i][0];
            int b = line[i][1];
            int e = line[i][2];
            
            for(int j = i + 1; j < n; j++) {                            
                
                int c = line[j][0];
                int d = line[j][1];
                int f = line[j][2];
                
                Point p = getPoint(a, b, e, c, d, f);
                
                if(p != null) {
                    points.add(p);
                    
                    minX = Math.min(minX, p.x);
                    minY = Math.min(minY, p.y);
                    
                    maxX = Math.max(maxX, p.x);
                    maxY = Math.max(maxY, p.y);
                }
            }
        }
        
        int width = (int) (maxX - minX);
        int height = (int) (maxY - minY);
        
        char[][] arr = new char[height + 1][width + 1];
        
        for(char[] row : arr) {
            Arrays.fill(row, '.');
        }
        
        for(Point p : points){
            int x = (int) (p.x - minX);
            int y = (int) (maxY - p.y);
            
            arr[y][x] = '*';
        }
        
        String[] ans = new String[arr.length];
        
        for(int i = 0; i < ans.length; i++) {
            ans[i] = new String(arr[i]);
        }
        
        return ans;
    }
    
    private Point getPoint(int a, int b, int e, int c, int d, int f) {
        
        long adbc = (long) a * d - (long) b * c;
        
        if(adbc == 0) {
            return null;
        }
        
        long bfed = (long) b * f - (long) e * d;
        long ecaf = (long) e * c - (long) a * f;
        
        if(bfed % adbc != 0 || ecaf % adbc != 0) {
            return null;
        }
        
        long x = bfed / adbc;
        long y = ecaf / adbc;
        
        return new Point(x, y);
    }
    
    private static class Point {
        
        long x, y;
        
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
}