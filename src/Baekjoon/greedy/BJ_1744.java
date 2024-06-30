package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <a href = "https://www.acmicpc.net/problem/1744">백준 1744번 - 그리디 : 수 묶기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1744%EB%B2%88-%EC%88%98-%EB%AC%B6%EA%B8%B0">velog</a>
 * @since 2024-06-27
 */
public class BJ_1744 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>(); //양수
        ArrayList<Integer> negative = new ArrayList<>(); //음수

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        positive.sort(Collections.reverseOrder()); //양수 내림차순 정렬
        negative.sort(null);                    //음수 오름차순 정렬

        int sum = 0;

        for (int i = 0; i < positive.size(); i++) {
            if (i + 1 < positive.size() && positive.get(i) != 1 && positive.get(i + 1) != 1) {
                sum += positive.get(i++) * positive.get(i);
            } else {
                sum += positive.get(i);
            }
        }

        for (int i = 0; i < negative.size(); i++) {
            if (i + 1 < negative.size()) {
                sum += negative.get(i++) * negative.get(i);
            } else {
                sum += negative.get(i);
            }
        }

        System.out.println(sum);
    }
}
