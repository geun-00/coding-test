package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * <a href = "https://www.acmicpc.net/problem/1715">백준 1715번 - 그리디 : 카드 정렬하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1715%EB%B2%88-%EC%B9%B4%EB%93%9C-%EC%A0%95%EB%A0%AC%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-06-16
 */
public class BJ_1715 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            qu.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;

        while (qu.size() > 1) {
            int card1 = qu.poll();
            int card2 = qu.poll();

            int sum = card1 + card2;
            result += sum;

            qu.offer(sum);
        }

        System.out.println(result);
    }
}
