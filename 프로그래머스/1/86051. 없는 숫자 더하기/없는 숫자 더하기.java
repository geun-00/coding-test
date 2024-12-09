import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        
        int[] arr = new int[10];
        
        for(int num : numbers){
            arr[num]++;
        }
        
        int ans = 0;
        
        for(int i = 0; i < 10; i++) {
            if(arr[i] == 0) ans += i;
        }
        
        return ans;
    }
}