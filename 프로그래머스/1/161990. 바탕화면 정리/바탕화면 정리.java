class Solution {
    public int[] solution(String[] wallpaper) {
        int maxTop = 51;
        int maxLeft = 51;
        int maxBottom = -1;
        int maxRight = -1;
        
        int n = wallpaper.length;
        int m = wallpaper[0].length();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    maxTop = Math.min(maxTop, i);
                    maxLeft = Math.min(maxLeft, j);
                    maxBottom = Math.max(maxBottom, i);
                    maxRight = Math.max(maxRight, j);
                }
            }
        }
        
        return new int[]{maxTop, maxLeft, maxBottom + 1, maxRight + 1};
    }
}