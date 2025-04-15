package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2258">백준 2258번 - 정육점</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2258%EB%B2%88-%EC%A0%95%EC%9C%A1%EC%A0%90">velog</a>
 * @since 2025-04-04
 */
public class BJ_2258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //무게
            arr[i][1] = Integer.parseInt(st.nextToken()); //가격
        }

        // 1
        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0]; //가격이 같으면 무게 내림차순
            }
            return a[1] - b[1];
        });

        int totalWeight = 0;    //지금까지 은혜가 산 고기 무게
        int prevPrice = -1;     //이전 덩어리의 가격
        int cost = 0;           //고기를 구매하기 위한 비용
        int ans = -1;

        for (int[] a : arr) {
            int w = a[0]; //무게
            int p = a[1]; //가격

            // 2
            if (prevPrice == p) {
                cost += p;
            } else {
                cost = p; //포인트
            }
            prevPrice = p;
            totalWeight += w;

            // 3
            if (totalWeight >= m) {
                if (ans == -1 || cost < ans) {
                    ans = cost;
                }
            }
        }

        System.out.println(ans);
    }
}
