package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1963">백준 1963번 - 그래프 탐색 : 소수 경로</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1963%EB%B2%88-%EC%86%8C%EC%88%98-%EA%B2%BD%EB%A1%9C">velog</a>
 * @since 2025-01-09
 */
public class BJ_1963 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        boolean[] primes = getPrimes();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b, primes))
              .append("\n");
        }

        System.out.print(sb);
    }

    private static String bfs(int a, int b, boolean[] primes) {

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(a, 0));

        boolean[] visit = new boolean[primes.length];
        visit[a] = true;

        while (!qu.isEmpty()) {

            State now = qu.poll();

            int num = now.num;
            int change = now.change;

            if (num == b) {
                return String.valueOf(change);
            }

            for (int i = 0; i < 4; i++) {

                int base = (int) Math.pow(10, i);
                int origin = num / base % 10; //현재 수 (i+1)자리 수의 원래 숫자

                //i+1의 자리 수를 j로 변경
                for (int j = 0; j < 10; j++) {

                    if (i == 3 && j == 0) continue; //천의 자리 수를 0으로 변경하는 경우
                    if (origin == j) continue;  //같은 수로 변경하는 경우

                    int nextNum = num - (origin * base) + (j * base);

                    if (!primes[nextNum] || visit[nextNum]) continue;

                    visit[nextNum] = true;
                    qu.offer(new State(nextNum, change + 1));
                }
            }
        }

        return "Impossible";
    }

    private static boolean[] getPrimes() {

        int range = 10000;

        boolean[] primes = new boolean[range];
        Arrays.fill(primes, true);

        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i <= Math.sqrt(range); i++) {
            if (!primes[i]) continue;

            for (int j = i * 2; j < range; j += i) {
                primes[j] = false;
            }
        }

        return primes;
    }

    static class State {
        int num, change;

        public State(int num, int change) {
            this.num = num;
            this.change = change;
        }
    }
}