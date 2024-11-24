package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14391">백준 14391번 - 비트 마스킹 : 종이 조각</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14391%EB%B2%88-%EC%A2%85%EC%9D%B4-%EC%A1%B0%EA%B0%81">velog</a>
 * @since 2024-11-22
 */
public class BJ_14391 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int max = 0;

        for (int i = 0; i < (1 << (n * m)); i++) {

            int total = 0;

            //가로 조각 계산
            for (int x = 0; x < n; x++) {

                int sum = 0;

                for (int y = 0; y < m; y++) {

                    int idx = x * m + y;

                    //가로 조각인 경우, 수를 이어붙임
                    if ((i & (1 << idx)) == 0) {
                        sum = sum * 10 + (arr[x][y] - '0');
                    }
                    //세로 조각을 만난 경우, 이어붙이던 가로 조각 누적 및 초기화
                    else {
                        total += sum;
                        sum = 0;
                    }
                }

                total += sum;
            }

            //세로 조각 계산
            for (int y = 0; y < m; y++) {

                int sum = 0;

                for (int x = 0; x < n; x++) {

                    int idx = x * m + y;

                    //세로 조각인 경우, 수를 이어붙임
                    if ((i & (1 << idx)) != 0) {
                        sum = sum * 10 + (arr[x][y] - '0');
                    }
                    //가로 조각을 만난 경우, 이어붙이던 세로 조각 누적 및 초기화
                    else {
                        total += sum;
                        sum = 0;
                    }
                }

                total += sum;
            }

            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}