package Programmers.level2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12973">프로그래머스 - Lv.2 : 짝지어 제거하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A7%9D%EC%A7%80%EC%96%B4-%EC%A0%9C%EA%B1%B0%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-06-21
 */
public class PairRemove {
    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
        System.out.println(solution("cdcd"));
    }

    private static int solution(String s) {

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
