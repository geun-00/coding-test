import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        
        for (int i : lost) arr[i-1]--;
        for (int i : reserve) arr[i-1]++;
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (i > 0 && arr[i - 1] > 1) {
                    arr[i]++;
                    arr[i-1]--;
                } 
                else if (i < n - 1 && arr[i + 1] > 1) {
                    arr[i]++;
                    arr[i+1]--;
                }
            }
            
            if (arr[i] > 0) answer++;
        }
        
        return answer;
    }
}