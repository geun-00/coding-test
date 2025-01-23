import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        return Arrays.stream(numbers)
                     .map(n -> func(n))
                     .toArray();
    }
    
    public long func(long n) {

        if (n % 2 == 0) {
            return n + 1;
        }

        long m = ~n & (n + 1);
        return n + m - (m >> 1);
    }
}