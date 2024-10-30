package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1092">백준 1092번 - 그리디 : 배</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1092%EB%B2%88-%EB%B0%B0">velog</a>
 *
 * @since 2024-10-26
 */
public class BJ_1092 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cranes = new int[n];
        for (int i = 0; i < n; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[] boxes = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes);
        Arrays.sort(boxes);

        //가장 무거운 박스가 제일 무거운 무게를 들 수 있는 크레인의 무게 제한보다 무거우면 절대 모든 박스를 옮길 수 없다.
        if (boxes[m - 1] > cranes[n - 1]) {
            System.out.println(-1);
            return;
        }

        int[] craneIdx = new int[n];
        Arrays.fill(craneIdx, m - 1);

        boolean[] isMoved = new boolean[m];

        int count = 0;

        while (m > 0) {

            for (int i = n - 1; i >= 0; i--) {

                if (m == 0) break;

                for (int j = craneIdx[i]; j >= 0; j--) {
                    if (!isMoved[j]) {
                        //현재 크레인이 옮길 수 있는 박스면 옮긴다.
                        if (cranes[i] >= boxes[j]) {
                            isMoved[j] = true;
                            m--;
                            break;
                        } else {
                            craneIdx[i]--;
                            //현재 크레인이 들 수 있는 박스의 시작점을 조정한다.
                            //첫 번째 while 문이 끝나고 나면 자신이 들 수 있는 무게의 박스부터 탐색해 나간다.
                        }
                    }
                }
            }
            count++;
        }

        System.out.println(count);
    }
}
