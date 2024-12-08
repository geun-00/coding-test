import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        
        int[][] pairs = {
            {0, 1, 2, 3},
            {0, 2, 1, 3},
            {0, 3, 1, 2}
        };
        
        for(int[] pair : pairs) {
            
            double slope1 = getSlope(dots[pair[0]], dots[pair[1]]);
            double slope2 = getSlope(dots[pair[2]], dots[pair[3]]);
            
            if(slope1 == slope2) return 1;
        }
        
        return 0;
    }
    
    public double getSlope(int[] dot1, int[] dot2) {
        
        int x1 = dot1[0];
        int y1 = dot1[1];
        
        int x2 = dot2[0];
        int y2 = dot2[1];
        
        return (y2 - y1) * 1.0 / (x2 - x1);
    }
}