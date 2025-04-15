package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2923">백준 2923번 - 숫자 게임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2923%EB%B2%88-%EC%88%AB%EC%9E%90-%EA%B2%8C%EC%9E%84">velog</a>
 * @since 2025-04-09
 */
public class BJ_2923 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] A = new int[101];
        int[] B = new int[101];

        // 1
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            A[Integer.parseInt(st.nextToken())]++;
            B[Integer.parseInt(st.nextToken())]++;

            sb.append(getMax(A, B, i + 1)).append("\n");
        }

        System.out.print(sb);
    }

    /**
     * 2
     * @param A 현우가 말한 A
     * @param B 현우가 말한 B
     * @param total 현재까지 나온 수의 개수
     * @return 가능한 쌍의 합 중 가장 큰 값
     */
    private static int getMax(int[] A, int[] B, int total) {
        //복사
        int[] x = Arrays.copyOf(A, A.length);
        int[] y = Arrays.copyOf(B, B.length);

        int xIdx = 1;
        int yIdx = 100;
        int max = 0;    //가장 큰 값
        int used = 0;   //총 사용된 수의 개수

        while (used < total) {
            while (xIdx <= 100 && x[xIdx] == 0) xIdx++; //작은 수에서 큰 수로
            while (yIdx >= 1 && y[yIdx] == 0) yIdx--;   //큰 수에서 작은 수로

            //둘 중 적게 나온 수만큼 쌍을 만들 수 있음
            int use = Math.min(x[xIdx], y[yIdx]);
            //가장 큰 쌍의 합 갱신
            max = Math.max(max, xIdx + yIdx);

            //쌍의 개수만큼 차감 및 사용된 수의 개수 누적
            x[xIdx] -= use;
            y[yIdx] -= use;
            used += use;
        }

        return max;
    }
}
