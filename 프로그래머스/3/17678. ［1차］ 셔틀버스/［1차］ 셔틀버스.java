import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crews = new PriorityQueue<>();
        for (String s : timetable) {
            String[] split = s.split(":");
            int hour = Integer.parseInt(split[0]) * 60;
            int minute = Integer.parseInt(split[1]);
            crews.offer(hour + minute);
        }

        int start = 60 * 9;
        List<Integer>[] list = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();

            while (list[i].size() < m && !crews.isEmpty() && crews.peek() <= start) {
                list[i].add(crews.poll());
            }
            start += t;
        }

        int ans = start - t;
        if (list[n - 1].size() >= m) {
            ans = list[n - 1].get(m - 1) - 1;
        }

        return String.format("%02d:%02d", ans / 60, ans % 60);
    }
}