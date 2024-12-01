import java.util.stream.IntStream;

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
        
        final int ansMax = max;
        
        return IntStream.range(0, 3)
                        .filter(i -> temp[i] == ansMax)
                        .map(i -> i + 1)
                        .toArray();
    }
}