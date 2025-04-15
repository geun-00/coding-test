package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10800">백준 10800번 - 컬러볼</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10800%EB%B2%88-%EC%BB%AC%EB%9F%AC%EB%B3%BC">velog</a>
 * @since 2025-04-06
 */
public class BJ_10800 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] balls = new int[n][3];

        // 1
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());
            balls[i][0] = i;
            balls[i][1] = color;
            balls[i][2] = size;
        }

        // 2
        Arrays.sort(balls, Comparator.comparing(ball -> ball[2]));

        int total = 0;                  //누적 점수
        int[] colorSum = new int[n];    //색깔별 누적
        int[] ans = new int[n];         //공마다 얻는 점수

        // 3
        for (int i = 0, j = 0; i < n; i++) {
            int num = balls[i][0];
            int color = balls[i][1];
            int size = balls[i][2];

            while (balls[j][2] < size) {
                total += balls[j][2];
                colorSum[balls[j][1]] += balls[j][2];
                j++;
            }

            ans[num] = total - colorSum[color];
        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for (int result : ans) {
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
