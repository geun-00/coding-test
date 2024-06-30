package Programmers.level2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/76502">프로그래머스 - Lv.2 : 괄호 회전하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B4%84%ED%98%B8-%ED%9A%8C%EC%A0%84%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-06-30
 */
public class RotateBracket {
    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
        System.out.println(solution("}]()[{"));
        System.out.println(solution("[)(]"));
        System.out.println(solution("}}}"));
    }

    private static int solution(String s) {

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (check(s)) {
                count++;
            }

            s = s.substring(1) + s.charAt(0);
        }
        return count;
    }

    private static boolean check(String s) {

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
