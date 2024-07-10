package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * <a href = "https://www.acmicpc.net/problem/21939">백준 21939번 - 해시 : 문제 추천 시스템 Version 1</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21939%EB%B2%88-%EB%AC%B8%EC%A0%9C-%EC%B6%94%EC%B2%9C-%EC%8B%9C%EC%8A%A4%ED%85%9C-Version-1">velog</a>
 *
 * @since 2024-07-08
 */
public class BJ_21939 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); //문제 개수

        TreeSet<Problem> problems = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>(); //key=문제 번호, value=난이도

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            problems.add(new Problem(p, l)); //O(log n)
            map.put(p, l); //O(1)
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            //난이도가 L인 문제 번호 P를 추가
            if ("add".equals(command)) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                problems.add(new Problem(p, l)); //O(log n)
                map.put(p, l); //O(1)

            //문제 번호 P를 제거
            } else if ("solved".equals(command)) {
                int p = Integer.parseInt(st.nextToken());

                problems.remove(new Problem(p, map.get(p))); //O(log n)
                map.remove(p); //O(1)

            } else if ("recommend".equals(command)) {
                int x = Integer.parseInt(st.nextToken());

                //가장 어려운 문제의 번호 출력
                if (x == 1) {
                    sb.append(problems.last().num).append("\n"); //O(1)

                //가장 쉬운 문제의 번호 출력
                } else if (x == -1) {
                    sb.append(problems.first().num).append("\n"); //O(1)
                }
            }
        }

        System.out.print(sb);
    }

    static class Problem implements Comparable<Problem> {
        int num, level; //문제 번호, 난이도

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return this.num - o.num;
            }

            return this.level - o.level;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof Problem)) return false;
            Problem problem = (Problem) object;
            return num == problem.num && level == problem.level;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, level);
        }
    }
}
