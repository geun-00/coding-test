package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12906">백준 12906번 - 해시 : 새로운 하노이 탑</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12906%EB%B2%88-%EC%83%88%EB%A1%9C%EC%9A%B4-%ED%95%98%EB%85%B8%EC%9D%B4-%ED%83%91">velog</a>
 * @since 2024-09-01
 */
public class BJ_12906 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Character, Integer> map = new HashMap<>(); //각 막대의 빈도수

        String[] init = new String[3]; //초기 상태

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            if (n > 0) {
                String s = st.nextToken();
                init[i] = s;
                for (int j = 0; j < n; j++) {
                    map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
                }
            }
            else {
                init[i] = ""; //null 방지
            }
        }

        //목표 상태
        String[] goal = new String[3];
        goal[0] = "A".repeat(map.getOrDefault('A', 0));
        goal[1] = "B".repeat(map.getOrDefault('B', 0));
        goal[2] = "C".repeat(map.getOrDefault('C', 0));

        System.out.println(solve(init, goal));
    }

    private static int solve(String[] init, String[] goal) {

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(init, 0));

        HashSet<String> visit = new HashSet<>();
        visit.add(Arrays.toString(init));

        while (!qu.isEmpty()) {

            State now = qu.poll();

            //목표 상태에 도달
            if (Arrays.equals(now.arr, goal)) {
                return now.moves;
            }

            //옮길 막대
            for (int from = 0; from < 3; from++) {
                //옮길 막대가 비어있으면 안됨
                if (!now.arr[from].isEmpty()) {

                    //옮겨질 막대
                    for (int to = 0; to < 3; to++) {
                        //같은 막대로 옮기면 안됨
                        if (from != to) {

                            //현재 상태 복사
                            String[] temp = now.arr.clone();

                            //가장 위에 있는 원판 꺼내기
                            char top = temp[from].charAt(temp[from].length() - 1);
                            //가장 위에 있는 원판 제거하기
                            temp[from] = temp[from].substring(0, temp[from].length() - 1);
                            //옮겨질 막대로 원판 옮기기
                            temp[to] += top;

                            if (visit.add(Arrays.toString(temp))) {
                                qu.offer(new State(temp, now.moves + 1));
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class State {
        String[] arr;
        int moves;

        public State(String[] arr, int moves) {
            this.arr = arr;
            this.moves = moves;
        }
    }
}