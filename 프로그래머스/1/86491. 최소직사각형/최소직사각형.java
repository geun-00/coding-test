class Solution {
    public int solution(int[][] sizes) {
        int w = 0;
        int h = 0;
        
        for (int[] card : sizes) {
            int max = Math.max(card[0], card[1]);
            int min = Math.min(card[0], card[1]);
            
            w = Math.max(w, max);
            h = Math.max(h, min);
        }
        
        return w * h;
    }
}