package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1722">백준 1722번 - 구현 : 순열의 순서</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1722%EB%B2%88-%EC%88%9C%EC%97%B4%EC%9D%98-%EC%88%9C%EC%84%9C">velog</a>
 * @since 2025-01-22
 */
public class BJ_1722 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] fact = new long[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        if (num == 1) {
            long k = Long.parseLong(st.nextToken()) - 1;

            List<Integer> numbers = getNumbers(n);

            StringBuilder sb = new StringBuilder();

            for (int i = n; i > 0; i--) {
                int index = (int) (k / fact[i - 1]);
                sb.append(numbers.get(index)).append(" ");
                numbers.remove(index);
                k %= fact[i - 1];
            }

            System.out.println(sb);
        }
        else {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> numbers = getNumbers(n);

            long ans = 1;

            for (int i = 0; i < n; i++) {
                int index = numbers.indexOf(arr[i]);
                ans += index * fact[n - 1 - i];
                numbers.remove(index);
            }

            System.out.println(ans);
        }
    }

    private static List<Integer> getNumbers(int n) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
