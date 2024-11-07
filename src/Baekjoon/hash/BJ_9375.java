package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9375">백준 9375번 - 해시 : 패션왕 신해빈</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9375%EB%B2%88-%ED%8C%A8%EC%85%98%EC%99%95-%EC%8B%A0%ED%95%B4%EB%B9%88">velog</a>
 * @since 2024-11-06
 */
public class BJ_9375 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();

                String s = st.nextToken();
                map.put(s, map.getOrDefault(s, 0) + 1);
            }

            int ans = 1;
            for (int num : map.values()) {
                //의상을 입는 경우에 더해 입지 않는 경우까지 계산해야 한다.
                ans *= num + 1;
            }

            sb.append(--ans).append("\n");
        }

        System.out.print(sb);
    }
}
