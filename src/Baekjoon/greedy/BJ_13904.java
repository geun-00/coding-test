package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13904">백준 13904번 - 그리디 : 과제</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13904%EB%B2%88-%EA%B3%BC%EC%A0%9C">velog</a>
 * @since 2024-08-07
 */
public class BJ_13904 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] tasks = new int[n][2];
        int maxDay = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tasks[i][0] = d;
            tasks[i][1] = w;

            maxDay = Math.max(maxDay, d);
        }

        Arrays.sort(tasks, (o1, o2) -> o2[1] - o1[1]);

        int sum = 0;
        int[] arr = new int[maxDay + 1];

        for (int[] task : tasks) {

            int d = task[0];
            int w = task[1];

            for (int i = d; i > 0; i--) {
                if (arr[i] == 0) {
                    arr[i] = w;
                    sum += w;
                    break;
                }
            }
        }

        System.out.println(sum);
    }
}
