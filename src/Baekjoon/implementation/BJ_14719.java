package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14719">백준 14719번 - 구현 : 빗물</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14719%EB%B2%88-%EB%B9%97%EB%AC%BC">velog</a>
 * @since 2024-08-17
 */
public class BJ_14719 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //각 위치 블록 높이
        int[] blocks = new int[w];

        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        //현재 위치에서 가장 높은 왼쪽 블록
        int[] left = new int[w];
        left[0] = blocks[0];

        for (int i = 1; i < w; i++) {
            left[i] = Math.max(left[i - 1], blocks[i]);
        }

        //현재 위치에서 가장 높은 오른쪽 블록
        int[] right = new int[w];
        right[w - 1] = blocks[w - 1];

        for (int i = w - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], blocks[i]);
        }

        int result = 0;

        for (int i = 0; i < w; i++) {
            result += Math.min(left[i], right[i]) - blocks[i];
        }

        System.out.println(result);
    }
}