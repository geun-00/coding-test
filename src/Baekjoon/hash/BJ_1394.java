package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * <a href = "https://www.acmicpc.net/problem/1394">백준 1394번 - 해시 : 암호</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1394%EB%B2%88-%EC%95%94%ED%98%B8">velog</a>
 * @since 2024-10-12
 */
public class BJ_1394 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String pw = br.readLine();

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i + 1);
        }

        int result = 0;
        int mod = 900528;
        int len = s.length();

        for (int i = 0; i < pw.length(); i++) {

            char c = pw.charAt(i);
            result = (result * len + map.get(c)) % mod;
        }

        System.out.println(result);

    }
}
