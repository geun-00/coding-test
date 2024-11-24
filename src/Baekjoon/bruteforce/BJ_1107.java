package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1107">백준 1107번 - 브루트포스 : 리모컨</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1107%EB%B2%88-%EB%A6%AC%EB%AA%A8%EC%BB%A8">velog</a>
 * @since 2024-11-20
 */
public class BJ_1107 {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        //초깂갓 : N까지 + 또는 - 만 누르면서 이동하는 경우
        int min = Math.abs(n - 100);

        for (int i = 0; i <= 1_000_000; i++) {

            if (check(i)) {
                //Math.abs(n - i) : 이동하려는 채널까지 + 또는 - 로만 이동할 때 버튼을 누르는 횟수
                //String.valueOf(i).length() : 채널 i 를 만드는 데 버튼을 누른 횟수
                min = Math.min(min, Math.abs(n - i) + String.valueOf(i).length());
            }
        }

        System.out.println(min);
    }

    private static boolean check(int num) {

        if (num == 0) {
            return !broken[0];
        }

        while (num > 0) {
            if (broken[num % 10]) {
                return false;
            }

            num /= 10;
        }

        return true;
    }
}