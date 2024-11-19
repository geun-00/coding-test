package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/7795">백준 7795번 - 투 포인터 : 먹을 것인가 먹힐 것인가</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-7795%EB%B2%88-%EB%A8%B9%EC%9D%84-%EA%B2%83%EC%9D%B8%EA%B0%80-%EB%A8%B9%ED%9E%90-%EA%B2%83%EC%9D%B8%EA%B0%80">velog</a>
 * @since 2024-11-18
 */
public class BJ_7795 {

    static int[] temp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] a = new int[n];
            int[] b = new int[m];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            temp = new int[Math.max(n, m)];

            mergeSort(a, 0, n - 1);
            mergeSort(b, 0, m - 1);

            int count = 0;
            int i = 0;  //A 포인터
            int j = 0;  //B 포인터

            while (i < n) {

                while (j < m && b[j] < a[i]) {
                    j++;
                }

                count += j;
                i++;
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    private static void mergeSort(int[] arr, int l, int r) {

        if (l >= r) return;

        int mid = (l + r) / 2;

        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {

        int i = l;
        int j = mid + 1;
        int idx = l;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[idx++] = arr[i++];
            } else {
                temp[idx++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[idx++] = arr[i++];
        }

        while (j <= r) {
            temp[idx++] = arr[j++];
        }

        for (int k = l; k <= r; k++) {
            arr[k] = temp[k];
        }
    }
}