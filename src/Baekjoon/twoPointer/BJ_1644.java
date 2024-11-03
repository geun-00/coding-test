package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <a href = "https://www.acmicpc.net/problem/1644">백준 1644번 - 투 포인터 : 소수의 연속합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1644%EB%B2%88-%EC%86%8C%EC%88%98%EC%9D%98-%EC%97%B0%EC%86%8D%ED%95%A9">velog</a>
 * @since 2024-11-02
 */
public class BJ_1644 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //1 ~ N까지 소수 판별
        boolean[] isPrime = getPrimes(n);

        //소수 목록 리스트
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        int m = primes.size();
        int[] sum = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            sum[i] = sum[i - 1] + primes.get(i - 1);
        }

        int left = 1, right = 1;
        int ans = 0;

        while (right <= m) {

            int res = sum[right] - sum[left - 1];

            if (res < n) {
                right++;
            } else if (res > n) {
                left++;
            } else {
                ans++;
                right++;
                left++;
            }
        }

        System.out.println(ans);
    }

    private static boolean[] getPrimes(int n) {

        boolean[] isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}
