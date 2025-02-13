package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10025">백준 10025번 - 투 포인터 : 게으른 백곰</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10025%EB%B2%88-%EA%B2%8C%EC%9C%BC%EB%A5%B8-%EB%B0%B1%EA%B3%B0">velog</a>
 * @since 2025-02-09
 */
public class BJ_10025 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[1_000_001];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[x] = g;
        }

        //초기 윈도우
        int sum = 0;
        for (int i = 0; i < Math.min(arr.length, 2 * k + 1); i++) {
            sum += arr[i];
        }

        //윈도우 이동하면서 계산
        int ans = sum;
        for (int left = 0, right = 2 * k + 1; right < arr.length; left++, right++) {
            sum -= arr[left];
            sum += arr[right];

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
