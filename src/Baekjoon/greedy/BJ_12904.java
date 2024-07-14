package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/12904">백준 12904번 - 그리디 : A와 B</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12904%EB%B2%88-A%EC%99%80-B">velog</a>
 * @since 2024-07-12
 */
public class BJ_12904 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        while (s.length() != t.length()) {
            if (t.endsWith("A")) {
                t = t.substring(0, t.length() - 1);
            } else {
                t = t.substring(0, t.length() - 1);
                t = new StringBuilder(t).reverse().toString();
            }
        }

        System.out.println(s.equals(t) ? 1 : 0);
    }
}