import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (o1, o2) -> {
            return o1[1] - o2[1];
        });


        int count = 0;
        int last = -30001;

        for (int[] r : routes) {
            if (r[0] > last) {
                count++;
                last = r[1];
            }
        }

        return count;
    }
}