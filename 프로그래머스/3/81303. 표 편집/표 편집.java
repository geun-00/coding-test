import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] isDel = new boolean[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        int cur = k;
        Deque<Integer> stack = new ArrayDeque<>();

        for (String s : cmd) {
            int x;
            switch (s.charAt(0)) {
                case 'U':
                    x = Integer.parseInt(s.substring(2));
                    for (int i = 0; i < x; i++) {
                        cur = prev[cur];
                    }
                    break;
                case 'D':
                    x = Integer.parseInt(s.substring(2));
                    for (int i = 0; i < x; i++) {
                        cur = next[cur];
                    }
                    break;
                case 'C':
                    stack.push(cur);
                    isDel[cur] = true;

                    if (next[cur] != -1) prev[next[cur]] = prev[cur];
                    if (prev[cur] != -1) next[prev[cur]] = next[cur];
                    cur = (next[cur] != -1) ? next[cur] : prev[cur];
                    break;
                case 'Z':
                    int index = stack.pop();
                    isDel[index] = false;

                    if (next[index] != -1) prev[next[index]] = index;
                    if (prev[index] != -1) next[prev[index]] = index;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(isDel[i] ? "X" : "O");
        }
        return sb.toString();
    }
}