package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1354">백준 1354번 - 해시 : 무한 수열 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1354%EB%B2%88-%EB%AC%B4%ED%95%9C-%EC%88%98%EC%97%B4-2">velog</a>
 * @since 2024-07-31
 */
public class BJ_1354 {

    static int p, q, x, y;
    static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        System.out.println(solve(n));
    }

    private static long solve(long n) {
        if (n <= 0) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }

        long result = solve(n / p - x) + solve(n / q - y);
        map.put(n, result);

        return result;
    }
}
