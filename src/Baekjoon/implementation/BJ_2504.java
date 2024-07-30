package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://www.acmicpc.net/problem/2504">백준 2504번 - 구현 : 괄호의 값</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2504%EB%B2%88-%EA%B4%84%ED%98%B8%EC%9D%98-%EA%B0%92">velog</a>
 * @since 2024-07-30
 */
public class BJ_2504 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();

        int result = 0;
        int temp = 1;

        for (int i = 0; i < s.length(); i++) {

            switch (s.charAt(i)) {
                case '(':
                    stack.push('(');
                    temp *= 2;
                    break;
                case '[':
                    stack.push('[');
                    temp *= 3;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        System.out.println(0);
                        return;
                    }
                    if (s.charAt(i - 1) == '(') {
                        result += temp;
                    }
                    stack.pop();
                    temp /= 2;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        System.out.println(0);
                        return;
                    }
                    if (s.charAt(i - 1) == '[') {
                        result += temp;
                    }
                    stack.pop();
                    temp /= 3;
                    break;
            }
        }

        System.out.println(!stack.isEmpty() ? 0 : result);
    }
}
