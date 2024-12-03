import java.util.*;

class Solution {
    
    static boolean[] used;
    static int[] arr;
    static HashSet<Integer> ans = new HashSet<>();
    
    public int solution(String numbers) {        
        
        int n = numbers.length();
        
        used = new boolean[n];
        arr = new int[n];
        
        for(int i = 0; i < n; i++){
            arr[i] = numbers.charAt(i) - '0';
        }
        
        solve(0);
        
        return ans.size();
    }
    
    public void solve(int now) {
        
        if(isPrime(now)) ans.add(now);
        
        for(int i = 0; i < arr.length; i++) {
            
            if(used[i]) continue;
            
            int next = now * 10 + arr[i];
            
            used[i] = true;
            solve(next);
            used[i] = false;
        }
    }
    
    public boolean isPrime(int num) {
        
        if(num <= 1) return false;
        
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        
        return true;
        
    }
}