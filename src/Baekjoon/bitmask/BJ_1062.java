package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1062">백준 1062번 - 비트 마스킹 : 가르침</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1062%EB%B2%88-%EA%B0%80%EB%A5%B4%EC%B9%A8">velog</a>
 * @since 2024-10-30
 */
public class BJ_1062 {

    static int[] words;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        words = new int[n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            words[i] = getBit(s);
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }

        int base = (1 << 'a' - 'a') | (1 << 'n' - 'a') | (1 << 't' - 'a') | (1 << 'i' - 'a') | (1 << 'c' - 'a');

        teach(0, 0, k - 5, base);

        System.out.println(max);
    }

    private static int getBit(String s) {
        int bit = 0;
        for (char c : s.toCharArray()) {
            bit |= (1 << c - 'a');
        }
        return bit;
    }

    private static void teach(int start, int depth, int limit, int mask) {
        if (depth == limit) {
            int read = readWords(mask);
            max = Math.max(max, read);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((mask & (1 << i)) == 0) {
                teach(i + 1, depth + 1, limit, mask | (1 << i));
            }
        }
    }

    private static int readWords(int mask) {

        int count = 0;

        for (int w : words) {
            if ((w & mask) == w) {
                count++;
            }
        }

        return count;
    }
}
