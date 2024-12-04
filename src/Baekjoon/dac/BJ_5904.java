package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/5904">백준 5904번 - 분할 정복 : Moo 게임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5904%EB%B2%88-Moo-%EA%B2%8C%EC%9E%84">velog</a>
 * @since 2024-12-01
 */
public class BJ_5904 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        int k = 0;
        long len = 3;

        while (len < n) {
            k++;
            len = len * 2 + (k + 3);
        }

        System.out.println(solve(n, k, len));
    }

    private static char solve(long n, int k, long len) {

        //k가 0까지 왔다면 moo 중 N번째 글자 반환
        if (k == 0) {
            return "moo".charAt((int) (n - 1));
        }

        long prevLen = (len - (k + 3)) / 2; //S(K-1) 부분의 길이
        long ms = prevLen + 1;              //중간 부분의 시작 인덱스
        long me = prevLen + (k + 3);        //중간 부분의 마지막 인덱스

        if (n <= prevLen) {
            return solve(n, k - 1, prevLen);
        }
        else if (ms <= n && n <= me) {
            return (n == ms) ? 'm' : 'o';
        }
        else {
            return solve(n - me, k - 1, prevLen);
        }
    }
}