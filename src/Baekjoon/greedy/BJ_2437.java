package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2437">백준 2437번 - 그리디 : 저울</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2437%EB%B2%88-%EC%A0%80%EC%9A%B8">velog</a>
 * @since 2024-07-09
 */
public class BJ_2437 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int min = 1; //추의 측정 가능 무게 범위

        for (int num : arr) {
            if (num > min) {
                break;
            }
            min += num;
        }

        System.out.println(min);
    }
}
