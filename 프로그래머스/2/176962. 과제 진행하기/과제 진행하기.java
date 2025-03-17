import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparingInt(task -> task.start));
        for (String[] plan : plans) {
            String name = plan[0];
            int start = Integer.parseInt(plan[1].substring(0, 2)) * 60 + Integer.parseInt(plan[1].substring(3));
            int remain = Integer.parseInt(plan[2]);

            tasks.offer(new Task(name, start, remain));
        }

        Deque<Task> deferredTasks = new ArrayDeque<>();

        Task currentTask = tasks.poll();

        String[] ans = new String[plans.length];
        int index = 0;

        while (!tasks.isEmpty() || !deferredTasks.isEmpty()) {
            Task nextTask = !tasks.isEmpty() ? tasks.peek() : null;

            if (nextTask == null || currentTask.start + currentTask.remain <= nextTask.start) {
                ans[index++] = currentTask.name;
                int end = currentTask.start + currentTask.remain;
                currentTask = null;

                while (!deferredTasks.isEmpty()) {
                    Task task = deferredTasks.pop();
                    if (nextTask != null && end + task.remain > nextTask.start) {
                        int diff = task.remain - (nextTask.start - end);
                        deferredTasks.push(new Task(task.name, nextTask.start, diff));
                        currentTask = tasks.poll();
                        break;
                    }
                    end += task.remain;
                    ans[index++] = task.name;
                }

                if (currentTask == null && !tasks.isEmpty()) {
                    currentTask = tasks.poll();
                }

            } else {
                int diff = nextTask.start - currentTask.start;
                deferredTasks.push(new Task(currentTask.name, nextTask.start, currentTask.remain - diff));
                currentTask = tasks.poll();
            }
        }

        if (currentTask != null) {
            ans[index] = currentTask.name;
        }

        return ans;
    }

    static class Task {
        String name;
        int start, remain;

        public Task(String name, int start, int remain) {
            this.name = name;
            this.start = start;
            this.remain = remain;
        }
    }
}