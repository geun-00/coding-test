class Solution {
    
    int[] arr;
    
    public int solution(int[] stones, int k) {
        
        arr = stones;
        
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }

        int s = min;
        int e = max;
        int result = 0;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (check(mid, k)) {
                s = mid + 1;
                result = Math.max(result, mid);
            } else {
                e = mid - 1;
            }
        }

        return result;
    }
    
    public boolean check(int mid, int k) {

        int count = 0;

        for (int i : arr) {
            if (i - mid < 0) {
                count++;
                if (count == k) {
                    return false;
                }
            } else {
                count = 0;
            }
        }

        return true;
    }
}
