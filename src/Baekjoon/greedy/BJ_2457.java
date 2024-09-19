package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2457">백준 2457번 - 그리디 : 공주님의 정원</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2457%EB%B2%88-%EA%B3%B5%EC%A3%BC%EB%8B%98%EC%9D%98-%EC%A0%95%EC%9B%90">velog</a>
 * @since 2024-09-13
 */
public class BJ_2457 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int blossomMonth = Integer.parseInt(st.nextToken());
            int blossomDay = Integer.parseInt(st.nextToken());
            int fallMonth = Integer.parseInt(st.nextToken());
            int fallDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Flower(
                    blossomMonth * 100 + blossomDay,
                    fallMonth * 100 + fallDay
            );
        }

        Arrays.sort(flowers);

        int start = 301;
        int end = 1201;

        int curEnd = 0;

        int count = 0;
        int index = 0;

        while (start < end) {

            boolean found = false;
            int nextEnd = curEnd;

            while (index < n && flowers[index].blossomDay <= start) {
                if (flowers[index].fallDay > nextEnd) {
                    nextEnd = flowers[index].fallDay;
                    found = true;
                }
                index++;
            }

            //11월 30일까지 중간에 커버할 수 있는 범위의 꽃이 없다면
            if (!found) {
                System.out.println(0);
                return;
            }

            count++;
            start = nextEnd;
            curEnd = nextEnd;
        }

        System.out.println(count);
    }

    static class Flower implements Comparable<Flower> {
        int blossomDay;
        int fallDay;

        public Flower(int blossomDay, int fallDay) {
            this.blossomDay = blossomDay;
            this.fallDay = fallDay;
        }

        @Override
        public int compareTo(Flower o) {
            return this.blossomDay - o.blossomDay;
        }
    }
}