package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/19637">백준 19637번 - 이분 탐색 : IF문 좀 대신 써줘</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-19637%EB%B2%88-IF%EB%AC%B8-%EC%A2%80-%EB%8C%80%EC%8B%A0-%EC%8D%A8%EC%A4%98">velog</a>
 * @since 2024-12-25
 */
public class BJ_19637 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, String> map = new HashMap<>();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            map.putIfAbsent(num, s);
            arr[i] = num;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());

/*
            //O(NM), 시간 초과
            for (int j = 0; j < n; j++) {
                if (arr[j] >= num) {
                    sb.append(map.get(arr[j])).append("\n");
                    break;
                }
            }
*/

            int low = lowerBound(arr, num);

            sb.append(map.get(arr[low])).append("\n");
        }

        System.out.print(sb);
    }

    private static int lowerBound(int[] arr, int num) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}