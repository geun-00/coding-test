import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        long s = 1;
        long e = (long) Arrays.stream(times).max().orElse(-1) * n;

        while (s < e) {
            long mid = (s + e) / 2;

            long sum = 0;
            for (int time : times) {
                sum += mid / time;
                if (sum >= n) {
                    break;
                }
            }

            if (sum >= n) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }
}