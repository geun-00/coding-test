package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12015">백준 12015번 - 이분 탐색 : 가장 긴 증가하는 부분 수열 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12015%EB%B2%88-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%EC%A6%9D%EA%B0%80%ED%95%98%EB%8A%94-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4-2">velog</a>
 * @since 2024-08-05
 */
public class BJ_12015 {

    static ArrayList<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
//        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
//            dp[i] = 1;
        }

        lis.add(arr[0]);

        for (int i = 1; i < n; i++) {

            int pos = binarySearch(arr[i]); //현재 값이 들어갈 적절한 위치 반환

            //대체
            if (pos < lis.size()) {
                lis.set(pos, arr[i]);
            //추가
            } else {
                lis.add(arr[i]);
            }
        }

        System.out.println(lis.size());
/*
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (arr[i] > arr[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
*/
    }

    private static int binarySearch(int val) {

        int s = 0;
        int e = lis.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;
            if (lis.get(mid) < val) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return s;
    }
}
