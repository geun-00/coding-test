package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://www.acmicpc.net/problem/5430">백준 5430번 - 구현 : AC</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5430%EB%B2%88-AC">velog</a>
 * @since 2024-06-18
 */
public class BJ_5430 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); //테스트 케이스 개수

        while (t-- > 0) {
            String p = br.readLine(); //수행할 함수
            int n = Integer.parseInt(br.readLine()); //배열에 들어있는 수의 개수
            String s = br.readLine(); //문자 형태의 정수 배열

            Deque<Integer> deque = new ArrayDeque<>();

            if (n > 0) { //원소가 존재할 때만

                //양쪽 끝 대괄호 없애주고 split
                String[] split = s.substring(1, s.length() - 1).split(",");

                for (String str : split) {
                    deque.offer(Integer.parseInt(str));
                }
            }

            boolean reverse = false;
            boolean error = false;

            for (char c : p.toCharArray()) {

                if (c == 'R') { //R이면 반대쪽 표시
                    reverse = !reverse;

                } else if (c == 'D') { //D면 reverse에 따라 앞 또는 뒤쪽에서 제거

                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }

                    if (reverse) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (error) {
                result.append("error").append("\n");
            } else {
                result.append("[");

                while (!deque.isEmpty()) {

                    if (reverse) {
                        result.append(deque.pollLast());
                    } else {
                        result.append(deque.pollFirst());
                    }

                    if (!deque.isEmpty()) {
                        result.append(",");
                    }
                }
                result.append("]").append("\n");
            }
        }

        System.out.print(result);
    }
}
