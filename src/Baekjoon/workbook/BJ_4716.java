package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4716">백준 4716번 - 풍선</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4716%EB%B2%88-%ED%92%8D%EC%84%A0">velog</a>
 * @since 2025-04-02
 */
public class BJ_4716 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (n == 0 && a == 0 && b == 0) {
                break;
            }

            int[][] arr = new int[n][3];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                arr[i][2] = Integer.parseInt(st.nextToken());
            }

            // 1
            Arrays.sort(arr, Comparator.comparingInt(o -> Math.abs(o[1] - o[2])));

            // 2
            int ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                int k = arr[i][0];
                int da = arr[i][1];
                int db = arr[i][2];

                // 3
                if (da < db) {
                    if (a - k > 0) {
                        ans += k * da;
                        a -= k;
                    }
                    else {
                        ans += a * da;
                        ans += (k - a) * db;
                        a = 0;
                    }
                }
                else {
                    if (b - k > 0) {
                        ans += k * db;
                        b -= k;
                    }
                    else {
                        ans += b * db;
                        ans += (k - b) * da;
                        b = 0;
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
