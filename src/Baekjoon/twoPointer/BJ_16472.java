package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/16472">백준 16472번 - 투 포인터 : 고냥이</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16472%EB%B2%88-%EA%B3%A0%EB%83%A5%EC%9D%B4">velog</a>
 * @since 2025-05-20
 */
public class BJ_16472 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        int[] arr = new int[26];

        int num = 0;
        int ans = 0;

        for (int s = 0, e = 0; e < input.length; e++) {
            // 1
            if (arr[input[e] - 'a'] == 0) {
                num++;
            }
            arr[input[e] - 'a']++;

            // 2
            while (num > n) {
                arr[input[s] - 'a']--;
                if (arr[input[s] - 'a'] == 0) {
                    num--;
                }
                s++;
            }

            // 3
            ans = Math.max(ans, (e - s + 1));
        }

        System.out.println(ans);
    }
}
