package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/16637">백준 16637번 - 구현 : 괄호 추가하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16637%EB%B2%88-%EA%B4%84%ED%98%B8-%EC%B6%94%EA%B0%80%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-10-30
 */
public class BJ_16637 {

    static char[] arr;
    static int n;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = br.readLine().toCharArray();

        solve(arr[0] - '0', 1);

        System.out.println(max);
    }

    private static void solve(int sum, int op) {
        if (op >= n) {
            max = Math.max(max, sum);
            return;
        }

        //괄호를 추가하지 않는 경우
        int next = calc(sum, arr[op], arr[op + 1] - '0');
        solve(next, op + 2);

        //다음 연산에 괄호 추가가 가능한 경우
        if (op + 2 < n) {

            //다음 연산 괄호 추가한 결과
            int result = calc(arr[op + 1] - '0', arr[op + 2], arr[op + 3] - '0');

            //다음 괄호 연산과 현재까지 합 연산
            next = calc(sum, arr[op], result);

            solve(next, op + 4);
        }
    }

    private static int calc(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }
}
