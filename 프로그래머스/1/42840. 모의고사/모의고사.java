class Solution {
    public int[] solution(int[] answers) {
        
        int[][] arr = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        
        int[] temp = new int[3];
        int max = 0;
        
        for(int i = 0; i < 3; i++) {
            
            for(int j = 0; j < answers.length; j++) {
                
                if(arr[i][j % arr[i].length] == answers[j]) {
                    temp[i]++;
                    
                    max = Math.max(max, temp[i]);
                }
            }
        }
        
        int count = 0;
        
        for(int i : temp) {
            if(i == max) {
                count++;
            }
        }
        
        int[] ans = new int[count];
        int idx = 0;
        
        for(int i = 0; i < 3; i++) {
            if(temp[i] == max) {
                ans[idx++] = i + 1;
            }
        }
        
        return ans;
    }
}