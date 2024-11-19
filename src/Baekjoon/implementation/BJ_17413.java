package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://www.acmicpc.net/problem/17413">백준 17413번 - 구현 : 단어 뒤집기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17413%EB%B2%88-%EB%8B%A8%EC%96%B4-%EB%92%A4%EC%A7%91%EA%B8%B0-2">velog</a>
 *
 * @since 2024-11-12
 */
public class BJ_17413 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length();) {

            if (s.charAt(i) == '<') {

                while (s.charAt(i) != '>') {
                    sb.append(s.charAt(i++));
                }
                sb.append(s.charAt(i++)); // '>' 삽입
            }
            else {

                //공백이나 특수 문자 나올 때까지 스택에 push
                while (i < s.length() && s.charAt(i) != ' ' && s.charAt(i) != '<') {
                    stack.push(s.charAt(i++));
                }

                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

                if (i < s.length() && s.charAt(i) == ' ') {
                    sb.append(" ");
                    i++;
                }
            }
        }

        System.out.println(sb);
    }
}
