import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        
        Arrays.sort(B);
        Arrays.sort(A);

        int n = A.length;

        int aIdx = 0;
        int bIdx = 0;

        int wins = 0;

        while (aIdx < n && bIdx < n) {
            if (B[bIdx] > A[aIdx]) {
                aIdx++;
                wins++;
            }
            bIdx++;
        }

        return wins;
    }
}