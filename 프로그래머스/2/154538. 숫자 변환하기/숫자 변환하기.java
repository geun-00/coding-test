import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        
        Queue<State> qu = new ArrayDeque<>();
        Set<Integer> visit = new HashSet<>();

        qu.offer(new State(x, 0));
        visit.add(x);

        while (!qu.isEmpty()) {
            State now = qu.poll();

            int num = now.num;
            int count = now.count;

            if (num == y) return count;

            int next;

            next = num + n;
            if (next <= y && visit.add(next)) {
                qu.offer(new State(next, count + 1));
            }

            next = num * 2;
            if (next <= y && visit.add(next)) {
                qu.offer(new State(next, count + 1));
            }

            next = num * 3;
            if (next <= y && visit.add(next)) {
                qu.offer(new State(next, count + 1));
            }
        }

        return -1;
    }
    
    static class State {
        int num, count;

        public State(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}