package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2613">백준 2613번 - 이분 탐색 : 숫자 구슬</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2613%EB%B2%88-%EC%88%AB%EC%9E%90-%EA%B5%AC%EC%8A%AC">velog</a>
 * @since 2025-05-24
 */
public class BJ_2613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        // 1
        int left = 1;
        int right = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
        }

        int minSum = 0;
        int[] ans = new int[m];

        // 2
        while (left <= right) {
            int mid = (left + right) / 2;

            int[] result = solve(arr, mid, m);

            if (result == null) {
                left = mid + 1;
            } else {
                minSum = mid;
                ans = result;
                right = mid - 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minSum).append("\n");

        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }

    private static int[] solve(int[] arr, int mid, int m) {
        int group = 1;  //그룹의 수
        int count = 0;  //구슬의 수
        int sum = 0;    //그룹의 합

        int[] ans = new int[m];
        int n = arr.length;

        // 3
        for (int i = 0; i < n; i++) {
            if (group > m || arr[i] > mid) {
                return null;
            }

            //n - i = 남은 구슬의 개수, m - group = 남은 그룹의 개수
            if (sum + arr[i] > mid || n - i <= m - group) {
                ans[group - 1] = count;
                group++;
                count = 1;
                sum = arr[i];
            } else {
                count++;
                sum += arr[i];
            }
        }

        if (group > m) {
            return null;
        }

        //마지막 그룹
        ans[m - 1] = count;
        return ans;
    }
}
