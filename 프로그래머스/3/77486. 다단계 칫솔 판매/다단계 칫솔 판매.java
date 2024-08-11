import java.util.*;

class Solution {
    
    HashMap<String, String> toParent = new HashMap<>();
    HashMap<String, Integer> gain = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int n = enroll.length;
        
        for (int i = 0; i < n; i++) {
            toParent.put(enroll[i], referral[i]);
            gain.put(enroll[i], 0);
        }

        for (int i = 0; i < seller.length; i++) {
            solve(seller[i], amount[i] * 100);
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = gain.get(enroll[i]);
        }

        return result;
    }
    
    public void solve(String s, int amount) {

        if (s.equals("-") || amount == 0 ) {
            return;
        }

        gain.put(s, gain.get(s) + amount - (amount / 10));
        solve(toParent.get(s), amount / 10);
    }
}