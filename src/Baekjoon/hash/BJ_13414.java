package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13414">백준 13414번 - 해시 : 수강신청</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13414%EB%B2%88-%EC%88%98%EA%B0%95%EC%8B%A0%EC%B2%AD">velog</a>
 * @since 2024-11-24
 */
public class BJ_13414 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        LinkedHashSet<String> set = new LinkedHashSet<>();

        for (int i = 0; i < l; i++) {
            String s = br.readLine();

            set.remove(s);

            set.add(s);
        }

        StringBuilder sb = new StringBuilder();

        for (String s : set) {
            sb.append(s).append("\n");

            k--;
            if (k == 0) break;
        }

        System.out.print(sb);
    }
}