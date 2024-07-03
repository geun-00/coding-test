package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2015">백준 2015번 - 해시 : 수들의 합 4</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2015%EB%B2%88-%EC%88%98%EB%93%A4%EC%9D%98-%ED%95%A9-4">velog</a>
 * @since 2024-07-02
 */
public class BJ_2015 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        int[] sum = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + arr[i];
        }

        long count = 0; // 1

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 2

        for (int i = 1; i <= n; i++) {

            if (map.containsKey(sum[i] - k)) { // == sum[j] - k
                count += map.get(sum[i] - k);
            }

            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }

/*
        // 비효율, O(N^2)
        // 부분합 계산을 위한 이중 반복문
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (sum[j] - sum[i - 1] == k) {
                    count++;
                }
            }
        }
*/

        System.out.println(count);
    }
}
