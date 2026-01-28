import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int n = numbers.length;
        String[] arr = new String[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = numbers[i] + "";
        }
        
        Arrays.sort(arr, (a, b) -> {
            return (a+b).compareTo(b+a);
        });
        
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        
        String ans = sb.toString();
        
        return ans.startsWith("0") ? "0" : ans;
    }
}