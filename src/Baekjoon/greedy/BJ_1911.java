package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1911">백준 1911번 - 그리디 : 흙길 보수하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1911%EB%B2%88-%ED%9D%99%EA%B8%B8-%EB%B3%B4%EC%88%98%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-10-16
 */
public class BJ_1911 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Puddle[] puddles = new Puddle[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            puddles[i] = new Puddle(s, e);
        }

        Arrays.sort(puddles);

        int count = 0; //필요한 널빤지 개수
        int now = 0;   //마지막 널빤지의 끝

        for (Puddle puddle : puddles) {

            //마지막 널빤지의 끝 >= 웅덩이 시작 위치이면 새로운 널빤지를 사용하지 않아도 된다.
            if (now < puddle.start) {
                now = puddle.start;
            }

            //필요한 만큼 널빤지 사용
            while (now < puddle.end) {
                now += l;
                count++;
            }
        }

        System.out.println(count);
    }

    static class Puddle implements Comparable<Puddle> {

        int start, end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Puddle o) {
            return this.start - o.start;
        }
    }
}
