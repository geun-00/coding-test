package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1365">백준 1365번 - 이분 탐색 : 꼬인 전깃줄</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1365%EB%B2%88-%EA%BC%AC%EC%9D%B8-%EC%A0%84%EA%B9%83%EC%A4%84">velog</a>
 * @since 2024-10-11
 */
public class BJ_1365 {

    static ArrayList<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);

        for (int i = 1; i < n; i++) {

            int pos = binarySearch(arr[i]);

            if (pos < lis.size()) {
                lis.set(pos, arr[i]);
            } else {
                lis.add(arr[i]);
            }
        }

        System.out.println(n - lis.size());
    }

    private static int binarySearch(int num) {

        int s = 0;
        int e = lis.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (lis.get(mid) < num) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return s;
    }
}
