import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Job> waitQu = new PriorityQueue<>((a, b) -> {
            if (a.time != b.time) return a.time - b.time;
            else if (a.start != b.start) return a.start - b.start;
            return a.id - b.id;
        });
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        int n = jobs.length;
        Job[] tasks = new Job[n];
        
        for (int i = 0; i < n; i++) {
            tasks[i] = new Job(jobs[i][1], jobs[i][0], i);
        }
        
        int index = 0;
        int total = 0;
        int prev = 0;
        
        while (index < n || !waitQu.isEmpty()) {
            
            while (index < n && prev >= jobs[index][0]) {
                waitQu.offer(tasks[index]);
                index++;
            }
            
            if (waitQu.isEmpty()) {
                prev = jobs[index][0];
                continue;
            }
            
            Job job = waitQu.poll();
            
            total += job.time + prev - job.start;        
            prev += job.time;
        }
        
        return total / n;
    }
    
    static class Job {
        int time, start, id;
        
        public Job(int t, int s, int i) {
            time = t;
            start = s;
            id = i;
        }
    }
}