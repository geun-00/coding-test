package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3151">백준 3151번 : 투 포인터 : 합이 0</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3151%EB%B2%88-%ED%95%A9%EC%9D%B4-0">velog</a>
 * @since 2024-11-21
 */
public class BJ_3151 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long count = 0;

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {

            //정렬되어 있기 때문에 양수가 시작되면 절대 0을 만들 수 없음
            if (arr[i] > 0) break;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = arr[i] + arr[left] + arr[right];

                if (sum == 0) {

                    //nC2
                    if (arr[left] == arr[right]) {
                        int t = right - left + 1;
                        count += t * (t - 1L) / 2;
                        break;
                    }

                    long l = 1, r = 1;

                    while (arr[left] == arr[left + 1]) {
                        left++;
                        l++;
                    }

                    while (arr[right] == arr[right - 1]) {
                        right--;
                        r++;
                    }

                    left++;
                    right--;

                    count += l * r;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);

    }
}