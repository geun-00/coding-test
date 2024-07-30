package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10942">백준 10942번 - DP : 팰린드롬?</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10942%EB%B2%88-%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC">velog</a>
 * @since 2024-07-28
 */
public class BJ_10942 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] palindrome = new boolean[n][n];

        //길이가 1~2인 팰린드롬 확인
        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
            if (i < n - 1) {
                if (arr[i] == arr[i + 1]) {
                    palindrome[i][i + 1] = true;
                }
            }
        }

        //길이가 3~N인 팰린드롬 확인
        for (int s = n - 3; s >= 0; s--) {
            for (int e = s + 2; e < n; e++) {
                if (arr[s] == arr[e] && palindrome[s + 1][e - 1]) {
                    palindrome[s][e] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            sb.append(palindrome[s][e] ? 1 : 0).append("\n");

/*          //비효율적인 방법
            boolean isPalindrome = true;

            while (s < e) {
                if (arr[s] != arr[e]) {
                    isPalindrome = false;
                    break;
                }
                s++;
                e--;
            }

            sb.append(isPalindrome ? 1 : 0).append("\n");
*/
        }

        System.out.print(sb);
    }
}
