package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/9082">백준 9082번 - 지뢰찾기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9082%EB%B2%88-%EC%A7%80%EB%A2%B0%EC%B0%BE%EA%B8%B0">velog</a>
 * @since 2025-04-20
 */
public class BJ_9082 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            char[] arr = br.readLine().toCharArray();
            char[] block = br.readLine().toCharArray();

            int[] number = new int[n];
            for (int j = 0; j < n; j++) {
                number[j] = arr[j] - '0';
            }

            int ans = 0;

            for (int j = 0; j < n; j++) {
                // 1
                if (block[j] == '*') {
                    for (int k = j - 1; k <= j + 1; k++) {
                        if (k >= 0 && k < n) {
                            number[k]--;
                        }
                    }
                    ans++;
                }
                else {
                    // 2
                    boolean flag = true;
                    for (int k = j - 1; k <= j + 1; k++) {
                        if (k >= 0 && k < n && number[k] <= 0) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (k >= 0 && k < n) {
                                number[k]--;
                            }
                        }
                        block[j] = '*';
                        ans++;
                    }
                }
            }

            System.out.println(ans);
        }
    }
}
