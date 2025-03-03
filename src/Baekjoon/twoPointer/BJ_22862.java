package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/22862">백준 22862번 - 투 포인터 : 가장 긴 짝수 연속한 부분 수열 (large)</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-22862%EB%B2%88-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%EC%A7%9D%EC%88%98-%EC%97%B0%EC%86%8D%ED%95%9C-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4-large">velog</a>
 * @since 2025-02-28
 */
public class BJ_22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int ans = 0;
        int even = 0;

        while (end < n) {

            if (arr[end] % 2 == 1) {
                if (k > 0) {
                    end++;
                    k--;
                }
                else {
                    if (arr[start++] % 2 == 0) even--;
                    else k++;
                }
            } else {
                end++;
                even++;
            }

            ans = Math.max(ans, even);
        }

        System.out.println(ans);
    }
}
