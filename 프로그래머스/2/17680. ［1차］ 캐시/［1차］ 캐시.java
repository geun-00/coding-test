import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        
         if (cacheSize == 0) {
            return cities.length * 5;
        }

        Queue<String> qu = new ArrayDeque<>();

        int time = 0;

        for (String s : cities) {
            String city = s.toLowerCase();

             if (qu.remove(city)) {
                qu.offer(city);
                time++;
            } else {
                if (qu.size() == cacheSize) {
                    qu.poll();
                }
                qu.offer(city);
                time += 5;
            }

        }

        return time;
    }
}