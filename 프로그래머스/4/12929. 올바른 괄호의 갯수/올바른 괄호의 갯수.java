import java.util.*;

class Solution {
    int ans = 0;
    
    public int solution(int n) {
        solve(n, n, "", n);
        return ans;
    }
    
    public void solve(int open, int close, String s, int n) {
        if (s.length() == n * 2) {
            ans += check(s);
            return;
        }

        if (open > 0) {
            solve(open - 1, close, s + "(", n);
        }
        if (close > 0) {
            solve(open, close - 1, s + ")", n);
        }
    }

    public int check(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return 0;
                }
                stack.pop();
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}