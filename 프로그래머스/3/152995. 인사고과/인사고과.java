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

        for (int[] score : scores) {

            if (max > score[1]) {
                if (Arrays.equals(score, wanho)) {
                    return -1;
                }
            } else {
                max = score[1];

                int sum = score[0] + score[1];
                if (sum > wanhoSum) {
                    result++;
                }
            }
        }
        
        return result;
    }
}