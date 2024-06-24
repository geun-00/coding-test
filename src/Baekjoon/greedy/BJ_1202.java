package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1202">백준 1202번 - 보석 도둑</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1202%EB%B2%88-%EB%B3%B4%EC%84%9D-%EB%8F%84%EB%91%91">velog</a>
 * @since 2024-06-23
 */
public class BJ_1202 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] bags = new int[k];
        Jewel[] jewels = new Jewel[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels[i] = new Jewel(m, v);
        }

        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);   //가방 정렬
        Arrays.sort(jewels); //보석 무게기준 정렬

        //내림차순 정렬 우선순위 큐
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0;
        int index = 0;

        for (int i = 0; i < k; i++) {
            while (index < n && bags[i] >= jewels[index].m) { //현재 가방에 넣을 수 있는 보석의 가치 저장
                qu.offer(jewels[index].v);
                index++;
            }
            if (!qu.isEmpty()) { //우선순위 큐로 가장 큰 값 빼냄
                sum += qu.poll();
            }
        }

        System.out.println(sum);

    }

    static class Jewel implements Comparable<Jewel> {

        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            if (this.m == o.m) {
                return o.v - this.v;
            }
            return this.m - o.m;
        }
    }
}
