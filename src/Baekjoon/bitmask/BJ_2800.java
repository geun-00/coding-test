package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <a href = "https://www.acmicpc.net/problem/2800">백준 2800번 - 비트 마스킹 : 괄호 제거</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2800%EB%B2%88-%EA%B4%84%ED%98%B8-%EC%A0%9C%EA%B1%B0">velog</a>
 * @since 2025-01-13
 */
public class BJ_2800 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Deque<Integer> stk = new ArrayDeque<>();
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stk.push(i);
            } else if (ch == ')') {
                pairs.add(new Pair(stk.pop(), i));
            }
        }

        List<String> ans = new ArrayList<>();

        for (int i = 1; i < (1 << pairs.size()); i++) {

            StringBuilder sb = new StringBuilder(s);

            for (int j = 0; j < pairs.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    sb.setCharAt(pairs.get(j).openIdx, ' ');
                    sb.setCharAt(pairs.get(j).closeIdx, ' ');
                }
            }

            ans.add(sb.toString().replace(" ", ""));
        }

        ans.stream()
           .distinct()
           .sorted()
           .forEach(System.out::println);
    }

    static class Pair {

        int openIdx;
        int closeIdx;

        public Pair(int openIdx, int closeIdx) {
            this.openIdx = openIdx;
            this.closeIdx = closeIdx;
        }
    }
}