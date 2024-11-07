package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1966">백준 1966번 - 시뮬레이션 : 프린터 큐</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1966%EB%B2%88-%ED%94%84%EB%A6%B0%ED%84%B0-%ED%81%90">velog</a>
 * @since 2024-11-06
 */
public class BJ_1966 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            //{index, priority}
            Queue<int[]> order = new ArrayDeque<>();
            PriorityQueue<Integer> priority = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());

                priority.offer(p);
                order.offer(new int[]{i, p});
            }

            int count = 0;

            while (!order.isEmpty()) {
                int[] now = order.poll();

                if (now[1] != priority.peek()) {
                    order.offer(now);
                }
                else {

                    priority.poll();
                    count++;

                    if (now[0] == m) {
                        sb.append(count).append("\n");
                        break;
                    }
                }
            }
        }

        System.out.print(sb);
    }
}
