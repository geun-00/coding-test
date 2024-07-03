import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int len = n * 2;
        
        int[] arr = new int[len];
        
        for(int i = 0; i < len; i++){
            arr[i] = elements[i % n];
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int length = 1; length <= n; length++){
            for(int i = 0; i < n; i++){
                int sum = 0;
                
                for(int j = i; j < i + length; j++){
                    sum += arr[j];
                }
                
                set.add(sum);
            }
        }
        
        return set.size();
    }
}