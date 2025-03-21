package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1039">백준 1039번 - 교환</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1039%EB%B2%88-%EA%B5%90%ED%99%98">velog</a>
 * @since 2025-03-14
 */
public class BJ_1039 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String n = st.nextToken();
        int k = Integer.parseInt(st.nextToken());

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(n, 0));

        String max = "-1";

        while (!qu.isEmpty()) {
            int size = qu.size();

            // 1
            Set<String> visit = new HashSet<>();

            for (int s = 0; s < size; s++) {
                State state = qu.poll();
                String num = state.num;
                int count = state.count;

                // 2
                if (count == k) {
                    max = (max.compareTo(num) < 0) ? num : max;
                    continue;
                }

                int len = num.length();

                // 3
                for (int i = 0; i < len - 1; i++) {
                    for (int j = i + 1; j < len; j++) {
                        char[] arr = num.toCharArray();

                        char tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;

                        String str = new String(arr);
                        if (count < k && arr[0] != '0' && visit.add(str)) {
                            qu.offer(new State(str, count + 1));
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }

    static class State {
        String num;
        int count;

        public State(String num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}
