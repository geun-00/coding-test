package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/8980">백준 8980번 - 그리디 : 택배</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-8980%EB%B2%88-%ED%83%9D%EB%B0%B0">velog</a>
 * @since 2024-09-30
 */
public class BJ_8980 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //마을 수
        int c = Integer.parseInt(st.nextToken()); //트럭의 용량

        int m = Integer.parseInt(br.readLine()); //보내는 박스 개수

        Box[] boxes = new Box[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            boxes[i] = new Box(from, to, amount);
        }

        Arrays.sort(boxes);

        int[] capacity = new int[n + 1]; //각 마을에서 트럭이 담을 수 있는 용량
        Arrays.fill(capacity, c);

        int total = 0;

        for (Box box : boxes) {

            int from = box.from;
            int to = box.to;
            int amount = box.amount;

            //from ~ to 구간에서 트럭이 실을 수 있는 최대 박스 수
            int max = Integer.MAX_VALUE;
            for (int i = from; i < to; i++) {
                max = Math.min(max, capacity[i]);
            }

            //트럭의 최대 용량을 넘지 않도록 조정
            //최대 용량을 넘지 않더라도 필요한 만큼만 배송해 용량을 최대한 아낀다.
            max = Math.min(max, amount);

            //from ~ to 구간 트럭에 실은 박스만큼 감소
            for (int i = from; i < to; i++) {
                capacity[i] -= max;
            }

            total += max;
        }

        System.out.println(total);
    }

    static class Box implements Comparable<Box> {
        int from, to, amount;

        public Box(int from, int to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public int compareTo(Box o) {
            if (this.to == o.to) {
                return this.from - o.from;
            }
            return this.to - o.to;
        }
    }
}