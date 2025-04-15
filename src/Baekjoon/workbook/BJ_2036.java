package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href = "https://www.acmicpc.net/problem/2036">백준 2036번 - 수열의 점수</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2036%EB%B2%88-%EC%88%98%EC%97%B4%EC%9D%98-%EC%A0%90%EC%88%98">velog</a>
 * @since 2025-04-10
 */
public class BJ_2036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 1
        List<Long> positive = new ArrayList<>(); //양수
        List<Long> negative = new ArrayList<>(); //음수
        int ones = 0; //1의 갯수

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());

            if (num == 1) ones++;
            else if (num <= 0) negative.add(num);
            else positive.add(num);
        }

        Collections.sort(positive);
        Collections.sort(negative);

        // 2
        long ans = 0;

        //양수 계산, 큰 수부터
        for (int i = positive.size() - 1; i >= 0; i -= 2) {
            if (i - 1 >= 0) {
                ans += (positive.get(i) * positive.get(i - 1));
            } else {
                ans += positive.get(i); //홀수개 일 때, 마지막 하나 남은 수
            }
        }

        //음수 계산, 작은 수부터
        for (int i = 0; i < negative.size(); i += 2) {
            if (i + 1 < negative.size()) {
                ans += (negative.get(i) * negative.get(i + 1));
            } else {
                ans += negative.get(i); //홀수개 일 때, 마지막 하나 남은 수
            }
        }

        ans += ones; //1은 그냥 더함
        System.out.println(ans);
    }
}
