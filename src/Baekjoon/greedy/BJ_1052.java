package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1052">백준 1052번 - 그리디 : 물병</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1052%EB%B2%88-%EB%AC%BC%EB%B3%91">velog</a>
 * @since 2024-08-04
 */
public class BJ_1052 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int count = 0;
        while (Integer.bitCount(n) > k) {
            n++;
            count++;
        }
        System.out.println(count);
    }
}
