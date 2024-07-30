class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int count = 0;

        int idx = 1;
        int range = w * 2 + 1;

        for (int s : stations) {
            if (s - w > idx) {
                int empty = s - w - idx;
                count += (int) Math.ceil((double) empty / range);
            }
            idx = s + w + 1;
        }

        if (idx <= n) {
            int empty = n - idx + 1;
            count += (int) Math.ceil((double) empty / range);
        }

        return count;
    }
}