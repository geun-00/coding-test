package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href = "https://www.acmicpc.net/problem/1132">백준 1132번 - 합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1132%EB%B2%88-%ED%95%A9">velog</a>
 * @since 2025-04-14
 */
public class BJ_1132 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] alphas = new long[10][2];

        // 1
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            alphas[s.charAt(0) - 'A'][0] = 1;   //첫 문자

            for (int j = 0; j < s.length(); j++) {
                alphas[s.charAt(j) - 'A'][1] += (long) Math.pow(10, (s.length() - j - 1));
            }
        }

        // 2
        Arrays.sort(alphas, Comparator.comparingLong(a -> a[1]));

        boolean[] used = new boolean[10];
        long ans = 0;

        // 3
        for (long[] alpha : alphas) {
            for (int j = (int) alpha[0]; j < 10; j++) {
                if (!used[j]) {
                    used[j] = true;
                    ans += alpha[1] * (long) j;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
