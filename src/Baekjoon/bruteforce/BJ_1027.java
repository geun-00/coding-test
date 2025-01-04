package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1027">백준 1027번 - 브루트포스 : 고층 건물</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1027%EB%B2%88-%EA%B3%A0%EC%B8%B5-%EA%B1%B4%EB%AC%BC">velog</a>
 * @since 2024-12-21
 */
public class BJ_1027 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] height = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int x1, y1, x2, y2;
        double slope, maxSlope, minSlope;

        for (int i = 0; i < n; i++) {

            int count = 0;

            x1 = i;
            y1 = height[i];

            //i번째 빌딩 기준 왼쪽 빌딩들과의 기울기 계산
            minSlope = Double.POSITIVE_INFINITY;
            for (int j = i - 1; j >= 0; j--) {
                x2 = j;
                y2 = height[j];

                slope = (double) (y2 - y1) / (x2 - x1);

                if (minSlope > slope) {
                    minSlope = slope;
                    count++;
                }
            }

            //i번째 빌딩 기준 오른쪽 빌딩들과의 기울기 계산
            maxSlope = Double.NEGATIVE_INFINITY;
            for (int j = i + 1; j < n; j++) {
                x2 = j;
                y2 = height[j];

                slope = (double) (y2 - y1) / (x2 - x1);

                if (maxSlope < slope) {
                    maxSlope = slope;
                    count++;
                }
            }

            ans = Math.max(ans, count);
        }

        System.out.println(ans);
    }
}
