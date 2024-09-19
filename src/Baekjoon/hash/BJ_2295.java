package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * <a href = "https://www.acmicpc.net/problem/2295">백준 2295번 - 해시 : 세 수의 합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2295%EB%B2%88-%EC%84%B8-%EC%88%98%EC%9D%98-%ED%95%A9">velog</a>
 *
 * @since 2024-09-18
 */
public class BJ_2295 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] u = new int[n];

        for (int i = 0; i < n; i++) {
            u[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(u);

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                set.add(u[i] + u[j]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (set.contains(u[i] - u[j])) {
                    System.out.println(u[i]);
                    return;
                }
            }
        }
    }
}
