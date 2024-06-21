import java.util.ArrayDeque;
import java.util.Deque;

class Solution
{
    public int solution(String s)
    {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
                continue;
            }

            stack.push(s.charAt(i));
        }

        return stack.isEmpty() ? 1 : 0;
    }
}