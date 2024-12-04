package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/17609">백준 17609번 - 투 포인터 : 회문</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17609%EB%B2%88-%ED%9A%8C%EB%AC%B8">velog</a>
 * @since 2024-12-02
 */
public class BJ_17609 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            String s = br.readLine();

            sb.append(isPalindrome(s)).append("\n");
        }

        System.out.print(sb);
    }

    private static int isPalindrome(String s) {

        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)) {
                    return 1;
                } else {
                    return 2;
                }
            }
            left++;
            right--;
        }

        return 0;
    }

    private static boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}