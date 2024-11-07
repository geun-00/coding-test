package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11723">백준 11723번 - 비트 마스킹 : 집합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11723%EB%B2%88-%EC%A7%91%ED%95%A9">velog</a>
 *
 * @since 2024-11-06
 */
public class BJ_11723 {

    //초기 세팅, 0b100000000000000000000
    static int set = 1 << 20;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (m-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x;

            switch (st.nextToken()) {
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    add(x);
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    remove(x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    sb.append(check(x)).append("\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    if (check(x) == 1) remove(x);
                    else add(x);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }

        System.out.print(sb);
    }

    private static void add(int x) {
        set |= (1 << x);
    }

    private static void remove(int x) {
        set &= ~(1 << x);
    }

    private static int check(int x) {
        return (set & (1 << x)) != 0 ? 1 : 0;
    }

    private static void all() {
        set |= ~(1 << 20);
    }

    private static void empty() {
        set &= (1 << 20);
    }
}