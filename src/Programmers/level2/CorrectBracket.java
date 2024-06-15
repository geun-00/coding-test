package Programmers.level2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12909">프로그래머스 - Lv.2 : 올바른 괄호</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%98%AC%EB%B0%94%EB%A5%B8-%EA%B4%84%ED%98%B8">velog</a>
 * @since 2024-06-16
 */
public class CorrectBracket {
    public static void main(String[] args) {
        String s1 = "()()";
        String s2 = "(())()";
        String s3 = ")()(";
        String s4 = "(()(";

        System.out.println(solution(s1));
        System.out.println(solution(s2));
        System.out.println(solution(s3));
        System.out.println(solution(s4));
    }

    private static boolean solution(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            }
            else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }
}
