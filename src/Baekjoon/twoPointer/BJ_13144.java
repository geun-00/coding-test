package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13144">백준 13144번 0 List of Unique Numbers</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13144%EB%B2%88-List-of-Unique-Numbers">velog</a>
 * @since 2025-01-02
 */
public class BJ_13144 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visit = new boolean[100_001];

        int left = 0, right = 0;
        long ans = 0;

        while (right < n) {

            if (!visit[arr[right]]) {
                visit[arr[right]] = true;
                ans += (right - left + 1);
                right++;
            }
            else {
                visit[arr[left]] = false;
                left++;
            }
        }

        System.out.println(ans);
    }
}