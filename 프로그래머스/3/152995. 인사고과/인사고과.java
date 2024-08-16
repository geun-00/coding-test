import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        
        int[] wanho = scores[0];
        int wanhoSum = wanho[0] + wanho[1];

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int result = 1;

        int max = scores[0][1];

        for (int i = 0; i < scores.length; i++) {

            int sum = scores[i][0] + scores[i][1];

            if (max > scores[i][1]) {

                if (Arrays.equals(scores[i], wanho)) {
                    return -1;
                }

                scores[i][0] = 0;
                scores[i][1] = 0;
            } else {
                max = scores[i][1];

                if (sum > wanhoSum) {
                    result++;
                }
            }
        }
        
        return result;
    }
}