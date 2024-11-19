package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/1541">백준 1541번 - 그리디 : 잃어버린 괄호</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1541%EB%B2%88-%EC%9E%83%EC%96%B4%EB%B2%84%EB%A6%B0-%EA%B4%84%ED%98%B8">velog</a>
 * @since 2024-11-12
 */
public class BJ_1541 {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split("-");

        boolean first = true;

        int ans = 0;

        for (String str : s) {

            int sum = getSum(str);

            if (first) {
                first = false;
                ans += sum;
            } else {
                ans -= sum;
            }
        }

        System.out.println(ans);
    }

    private static int getSum(String str) {

        String[] split = str.split("\\+"); //escape

        int sum = 0;

        for (String s : split) {
            sum += Integer.parseInt(s);
        }

        return sum;
    }
}