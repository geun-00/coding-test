package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9576">백준 9576번 - 그리디 : 책 나눠주기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9576%EB%B2%88-%EC%B1%85-%EB%82%98%EB%88%A0%EC%A3%BC%EA%B8%B0">velog</a>
 *
 * @since 2024-10-22
 */
public class BJ_9576 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            boolean[] check = new boolean[n + 1];
            int[][] arr = new int[m][2];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                arr[i][0] = a;
                arr[i][1] = b;
            }

            //b를 기준으로 정렬
            Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));

            int count = 0;

            for (int[] s : arr) {

                int a = s[0];
                int b = s[1];

                for (int i = a; i <= b; i++) {
                    if (check[i]) continue; //이미 배정된 책은 continue

                    check[i] = true;
                    count++;
                    break;
                }
            }

            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
