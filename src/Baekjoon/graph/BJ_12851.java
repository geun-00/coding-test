package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12851">백준 12851번 - 그래프 탐색 : 숨바꼭질 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12851%EB%B2%88-%EC%88%A8%EB%B0%94%EA%BC%AD%EC%A7%88-2">velog</a>
 *
 * @since 2024-09-14
 */
public class BJ_12851 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        //해당 위치에 도착하는 최소시간
        int[] time = new int[100_001];
        time[n] = 1;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(n);

        int min = Integer.MAX_VALUE;
        int count = 0;

        while (!qu.isEmpty()) {
            int now = qu.poll();

            //불필요한 탐색 방지
            if (time[now] > min) {
                break;
            }

            for (int next : new int[]{now + 1, now - 1, now * 2}) {

                if (0 <= next && next <= 100_000) {

                    if (next == k) {
                        count++;
                        min = time[now];
                    }
                    //처음 도착하는 경우
                    //이미 도착한 적이 있지만 같은 시간이 도착하는 경우 중복 접근을 허용하여 여러 경로 탐색
                    if (time[next] == 0 || time[next] == time[now] + 1) {
                        qu.offer(next);
                        time[next] = time[now] + 1;
                    }
                }
            }
        }

        System.out.println(min + "\n" + count);
    }
}
