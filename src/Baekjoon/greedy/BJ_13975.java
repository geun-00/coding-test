package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13975">백준 13975번 - 그리디 : 파일 합치기 3</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13975%EB%B2%88-%ED%8C%8C%EC%9D%BC-%ED%95%A9%EC%B9%98%EA%B8%B0-3">velog</a>
 * @since 2024-09-22
 */
public class BJ_13975 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            int k = Integer.parseInt(br.readLine());

            PriorityQueue<Long> qu = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < k; i++) {
                long num = Long.parseLong(st.nextToken());

                qu.offer(num);
            }

            long total = 0;

            while (qu.size() > 1) {

                long n1 = qu.poll();
                long n2 = qu.poll();

                long sum = n1 + n2;
                total += sum;

                qu.offer(sum);
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb);
    }
}
