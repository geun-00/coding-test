package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2812">백준 2812번 - 그리디 : 크게 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2812%EB%B2%88-%ED%81%AC%EA%B2%8C-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2024-07-17
 */
public class BJ_2812 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Deque<Integer> stack = new ArrayDeque<>();

        String s = br.readLine();

        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';

            while (!stack.isEmpty() && stack.peek() < num && 0 < k) {
                stack.pop();
                k--;
            }

            stack.push(num);
        }

        while (0 < k) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        System.out.println(sb);
    }
}
