package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10090">백준 10090번 - 분할 정복 : Counting Inversions</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10090%EB%B2%88-Counting-Inversions">velog</a>
 * @since 2024-11-22
 */
public class BJ_10090 {

    static int[] temp;
    static long ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        temp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(arr, 0, n - 1);

        System.out.println(ans);

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
            if (arr[i] < arr[j]) {
                temp[idx++] = arr[i++];
            } else {
                temp[idx++] = arr[j++];
                ans += mid - i + 1; //역순 쌍
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