import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] arr = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        
        List<Integer> list = new ArrayList<>();
        int max = 0;
        
        for (int i = 0; i < 3; i++) {

            int count = 0;
            
            for (int j = 0; j < answers.length; j++) {
                if (arr[i][j  % arr[i].length] == answers[j]) {
                    count++;
                }
            }
            
            if (max < count) {
                max = count;
                list.clear();
                list.add(i + 1);
            } else if (max == count) {
                list.add(i + 1);
            }
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}