package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1082">백준 1082번 - 방 번호</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1082%EB%B2%88-%EB%B0%A9-%EB%B2%88%ED%98%B8">velog</a>
 * @since 2025-04-08
 */
public class BJ_1082 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] price = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        // 1 비용이 작으면서 가장 큰 수 찾기
        int minCost = 50;
        int minNum = -1;
        for (int i = 0; i < n; i++) {
            if (price[i] <= minCost) {
                minCost = price[i];
                minNum = i;
            }
        }

        // 2. 최대 자릿수 계산
        int length = m / minCost;

        // 3. minNum으로 초기화
        int[] result = new int[length];
        Arrays.fill(result, minNum);
        m -= minCost * length; //남은 돈

        // 4. 앞에서부터 가능한 가장 큰 숫자로 교체
        int s = 0;
        for (int i = 0; i < length; i++) {
            //큰 수부터 탐색
            for (int j = n - 1; j >= 0; j--) {
                if (price[j] <= m + minCost) {
                    result[i] = j;
                    m += minCost;
                    m -= price[j];
                    break;
                }
            }

            if (result[s] == 0) {
                s++;
                m += minCost;
            }
        }

        // 5. 출력
        if (s == length) {
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s; i < length; i++) {
            sb.append(result[i]);
        }
        System.out.println(sb);
    }
}