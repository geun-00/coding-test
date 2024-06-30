import java.util.*;

class Solution {
    public int solution(String s) {
       int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (check(s)) {
                count++;
            }

            s = s.substring(1) + s.charAt(0); //왼쪽으로 회전
        }
        return count;
    }
    
     public boolean check(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            char now = s.charAt(i);

            if (now == '(' || now == '[' || now == '{') {
                stack.push(now);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (now == ')' && stack.peek() == '(') {
                    stack.pop();
                }
                else if (now == ']' && stack.peek() == '[') {
                    stack.pop();
                }
                else if (now == '}' && stack.peek() == '{') {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
     }
}