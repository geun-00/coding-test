import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        
        if (n > s) {
            return new int[]{-1};
        }

        int[] result = new int[n];

        int base = s / n;
        int remain = s % n;

        Arrays.fill(result, base);

        for (int i = 0; i < remain; i++) {
            result[i]++;
        }
        
        Arrays.sort(result);

        return result;
    }
}