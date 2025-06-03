import java.util.*;

class Solution {
    public int[] solution(int[][] score) {
        int n = score.length;
        double[][] arr = new double[n][2];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = (score[i][0] + score[i][1]) / 2.0;
            arr[i][1] = i;
        }
        
        Arrays.sort(arr, (a, b) -> Double.compare(b[0], a[0]));
        
        int rank = 1;
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i][0] == arr[i - 1][0]) {
                ans[(int) arr[i][1]] = ans[(int) arr[i - 1][1]];
            } 
            else {
                ans[(int) arr[i][1]] = rank;
            }
            
            rank++;
        }

        return ans;
    }
}