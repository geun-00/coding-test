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

        int max = scores[0][1];

        for (int i = 1; i < scores.length; i++) {

            if (max > scores[i][1]) {

                if (scores[i][0] == wanho[0] && scores[i][1] == wanho[1]) {
                    return -1;
                }

                scores[i][0] = 0;
                scores[i][1] = 0;
            } else {
                max = scores[i][1];
            }
        }

        Arrays.sort(scores,(o1, o2) -> {
            int sum1 = o1[0] + o1[1];
            int sum2 = o2[0] + o2[1];

            return sum2 - sum1;
        });

        int result = 1;


        for (int[] score : scores) {
            int sum = score[0] + score[1];

            if (sum > wanhoSum) {
                result++;
            } else {
                return result;
            }
        }
        return -1;
    }
}