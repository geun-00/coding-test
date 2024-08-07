import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> jobQu = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int jobNums = jobs.length;

        int curTime = 0;
        int jobIdx = 0;
        int total = 0;
        int process = 0;

        while (process < jobNums) {

            while (jobIdx < jobNums && jobs[jobIdx][0] <= curTime) {
                jobQu.offer(jobs[jobIdx++]);
            }

            if (!jobQu.isEmpty()) {
                int[] job = jobQu.poll();
                total += job[1] + curTime - job[0];
                curTime += job[1];
                process++;
            } else {
                curTime=jobs[jobIdx][0];
            }
        }

        return total / jobNums;
    }
}