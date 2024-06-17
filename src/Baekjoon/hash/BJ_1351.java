package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1351">백준 1351번 - 해시 : 무한 수열</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1351-%EB%AC%B4%ED%95%9C-%EC%88%98%EC%97%B4">velog</a>
 * @since 2024-06-17
 */
public class BJ_1351 {

    static  HashMap<Long, Long> map = new HashMap<>();
    static int p, q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map.put(0L, 1L);

        System.out.println(solve(n));
    }

    private static long solve(long n) {

        if (map.containsKey(n)) { //저장된 값이 있다면 이후 계산을 거치지 않는다.
            return map.get(n);
        }

        long result = solve(n / p) + solve(n / q);
        map.put(n, result); //메모이제이션

        return result;
    }
}
