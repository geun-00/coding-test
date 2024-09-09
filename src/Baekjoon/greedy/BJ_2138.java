package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <a href = "https://www.acmicpc.net/problem/2138">백준 2138번 - 그리디 : 전구와 스위치</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2138%EB%B2%88-%EC%A0%84%EA%B5%AC%EC%99%80-%EC%8A%A4%EC%9C%84%EC%B9%98">velog</a>
 * @since 2024-09-05
 */
public class BJ_2138 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[] init = new boolean[n];
        boolean[] target = new boolean[n];

        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            init[i] = s.charAt(i) == '1';
        }

        s = br.readLine();
        for (int i = 0; i < n; i++) {
            target[i] = s.charAt(i) == '1';
        }

        int firstOff = solve(init, target, n, false); //첫번째 전구를 누르지 않은 경우
        int firstOn = solve(init, target, n, true); //첫번째 전구를 누른 경우

        int result = Math.min(firstOff, firstOn);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static int solve(boolean[] init, boolean[] target, int n, boolean first) {

        int count = 0; //전구를 누른 횟수
        boolean[] lights = init.clone();

        if (first) {
            toggle(lights, n, 0);
            count++;
        }

        for (int i = 1; i < n; i++) { //2번째 전구부터 이전 번째 전구 비교
            if (lights[i - 1] != target[i - 1]) {
                toggle(lights, n, i);
                count++;
            }
        }

        if (!Arrays.equals(lights, target)) {
            return Integer.MAX_VALUE;
        }

        return count;
    }

    private static void toggle(boolean[] lights, int n, int idx) {
        lights[idx] = !lights[idx];

        if (idx > 0) {
            lights[idx - 1] = !lights[idx - 1];
        }
        if (idx < n - 1) {
            lights[idx + 1] = !lights[idx + 1];
        }
    }
}
