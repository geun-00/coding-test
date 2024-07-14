package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1327">백준 1327번 - 해시 : 소트 게임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1327%EB%B2%88-%EC%86%8C%ED%8A%B8-%EA%B2%8C%EC%9E%84">velog</a>
 *
 * @since 2024-07-14
 */
public class BJ_1327 {

    static int n, k;
    static String correct;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().replaceAll(" ", "").toCharArray();
        String init = new String(arr);
        Arrays.sort(arr);

        correct = new String(arr);
        System.out.println(solve(init));
    }

    private static int solve(String s) {

        Queue<State> qu = new ArrayDeque<>();
        HashSet<String> visit = new HashSet<>();

        qu.offer(new State(s, 0));

        while (!qu.isEmpty()) {
            State now = qu.poll();

            if (now.str.equals(correct)) {
                return now.count;
            }

            if (visit.add(now.str)) {
                //오른쪽으로 K개의 수 뒤집기
                for (int i = 0; i <= n - k; i++) {
                    qu.offer(new State(reverse(now.str, i, i + k), now.count + 1));
                }
            }
        }

        return -1;
    }

    private static String reverse(String str, int s, int e) {
        StringBuilder sb = new StringBuilder();

        sb.append(str, 0, s);

        String temp = str.substring(s, e); //k 길이의 문자열
        for (int i = k - 1; i >= 0; i--) {
            sb.append(temp.charAt(i));
        }

        sb.append(str, e, n);

        return sb.toString();
    }

    static class State {
        String str;
        int count;

        public State(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
