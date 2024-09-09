package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1208">백준 1208번 - 이분 탐색 : 부분수열의 합 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1208%EB%B2%88-%EB%B6%80%EB%B6%84%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9-2">velog</a>
 * @since 2024-09-08
 */
public class BJ_1208 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = Arrays.copyOfRange(arr, 0, n / 2);
        int[] right = Arrays.copyOfRange(arr, n / 2, n);

        ArrayList<Integer> leftSum = new ArrayList<>();
        ArrayList<Integer> rightSum = new ArrayList<>();

        getSums(left, 0, 0, leftSum);
        getSums(right, 0, 0, rightSum);

        rightSum.sort(null);

        long count = 0;

        for (int sum : leftSum) {
            int target = s - sum;

            count += upperBound(target, rightSum) - lowerBound(target, rightSum);
        }

        if (s == 0) {
            count--;
        }

        System.out.println(count);
    }

    private static int upperBound(int target, ArrayList<Integer> rightSum) {

        int left = 0;
        int right = rightSum.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (rightSum.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static int lowerBound(int target, ArrayList<Integer> rightSum) {

        int left = 0;
        int right = rightSum.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (rightSum.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static void getSums(int[] arr, int idx, int sum, ArrayList<Integer> list) {
        if (idx == arr.length) {
            list.add(sum);
            return;
        }

        //해당 원소를 포함하는 경우
        getSums(arr, idx + 1, sum + arr[idx], list);

        //해당 원소를 포함하지 않는 경우(공집합 포함)
        getSums(arr, idx + 1, sum, list);
    }
}