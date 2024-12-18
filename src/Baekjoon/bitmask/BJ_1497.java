package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1497">백준 1497번 - 비트 마스킹 : 기타콘서트</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1497%EB%B2%88-%EA%B8%B0%ED%83%80%EC%BD%98%EC%84%9C%ED%8A%B8">velog</a>
 * @since 2024-12-12
 */
public class BJ_1497 {

    static int n, m;
    static int maxPossible = 0;
    static int minUsed = Integer.MAX_VALUE;

    static long[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String song = st.nextToken();

            for (int j = 0; j < m; j++) {
                if (song.charAt(j) == 'Y') {
                    arr[i] |= (1L << (m - j - 1));
                }
            }
        }

        solve(0, 0, 0);

        System.out.println(maxPossible == 0 ? -1 : minUsed);
    }

    private static void solve(int depth, long possible, int used) {

        int possibleCount = Long.bitCount(possible);

        if (possibleCount == maxPossible) {
            minUsed = Math.min(minUsed, used);
        }
        else if (possibleCount > maxPossible) {
            maxPossible = possibleCount;
            minUsed = used;
        }

        if (depth == n) return;

        //현재 기타 사용 X
        solve(depth + 1, possible, used);

        //현재 기타 사용 O
        solve(depth + 1, possible | arr[depth], used + 1);
    }
}