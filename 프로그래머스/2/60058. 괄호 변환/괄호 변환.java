import java.util.*;

class Solution {
    public String solution(String p) {
        return solve(p);
    }
    
    private String solve(String w) {

        if (w.isEmpty()) {
            return w;
        }

        String u = "";
        String v = "";

        int count = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') count--;
            else count++;

            if (count == 0) {
                u = w.substring(0, i + 1);
                v = w.substring(i + 1);
                break;
            }
        }

        if (isCorrect(u)) {
            return u + solve(v);
        }

        String temp = "(" + solve(v) + ")";
        u = u.substring(1, u.length() - 1);
        u = turn(u);

        return temp + u;
    }

    private String turn(String s) {

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') sb.append(")");
            else sb.append("(");
        }

        return sb.toString();
    }

    private boolean isCorrect(String s) {

        Deque<Character> stk = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stk.push(c);
            } else {
                if (stk.isEmpty()) {
                    return false;
                }
                stk.pop();
            }
        }

        return stk.isEmpty();
    }
}