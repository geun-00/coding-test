import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        HashSet<Integer>[] dp = new HashSet[8];
        
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashSet<>();
            dp[i].add(Integer.parseInt(String.valueOf(N).repeat(i + 1)));
        }

        for (int i = 0; i < dp.length; i++) {
            
            for (int j = 0; j < i; j++) {

                for (int a : dp[j]) {
                    for (int b : dp[i - j - 1]) {

                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(b - a);
                        dp[i].add(b * a);

                        if (a != 0) {
                            dp[i].add(b / a);
                        }
                        if (b != 0) {
                            dp[i].add(a / b);
                        }
                    }                                                        
                }
            }
            
            if (dp[i].contains(number)) {
                 return i + 1;
            }
            
        }

        return -1;
    }
}