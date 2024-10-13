package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17140">백준 17140번 - 구현 : 이차원 배열과 연산</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17140%EB%B2%88-%EC%9D%B4%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EA%B3%BC-%EC%97%B0%EC%82%B0">velog</a>
 * @since 2024-10-06
 */
public class BJ_17140 {

    static class Info implements Comparable<Info> {

        int num, count;

        public Info(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Info o) {
            if (this.count == o.count) {
                return this.num - o.num;
            }
            return this.count - o.count;
        }
    }

    static int[][] A = new int[101][101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (A[r][c] != k) {

            //100초가 지나도 해결할 수 없을 때
            if (time > 100) {
                System.out.println(-1);
                return;
            }

            //행의 개수 >= 열의 개수인 경우 R 연산, 아닌 경우 C 연산
            if (countRows() >= countCols()) {
                oper_R();
            } else {
                oper_C();
            }

            time++;
        }

        System.out.println(time);
    }

    private static int countRows() {

        int rows = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (A[i][j] != 0) {
                    rows++;
                    break;
                }
            }
        }
        return rows;
    }

    private static int countCols() {

        int cols = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (A[j][i] != 0) {
                    cols++;
                    break;
                }
            }
        }
        return cols;
    }

    private static void oper_R() {

        HashMap<Integer, Integer> map;

        for (int i = 1; i <= 100; i++) {

            map = new HashMap<>();

            //각각의 수 빈도수 계산
            for (int j = 1; j <= 100; j++) {
                if (A[i][j] != 0) {
                    map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                }
            }

            //등장 횟수, 수 오름차순 정렬
            Info[] infos = new Info[map.keySet().size()];
            int idx = 0;
            for (int key : map.keySet()) {
                infos[idx++] = new Info(key, map.get(key));
            }
            Arrays.sort(infos);

            idx = 1;

            //정렬된 결과를 배열에 넣기
            for (Info info : infos) {
                if (idx > 100) { //처음 100개를 제외한 나머지는 버려야 하기 때문에 100이 넘으면 중단
                    break;
                }
                A[i][idx++] = info.num;

                if (idx > 100) {
                    break;
                }
                A[i][idx++] = info.count;
            }

            //나머지 공간 0으로 채우기
            while (idx <= 100) {
                A[i][idx++] = 0;
            }
        }
    }

    private static void oper_C() {

        HashMap<Integer, Integer> map;

        for (int i = 1; i <= 100; i++) {

            map = new HashMap<>();

            //각각의 수 빈도수 계산
            for (int j = 1; j <= 100; j++) {
                if (A[j][i] != 0) {
                    map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
                }
            }

            Info[] infos = new Info[map.keySet().size()];
            int idx = 0;
            for (int key : map.keySet()) {
                infos[idx++] = new Info(key, map.get(key));
            }
            Arrays.sort(infos);

            idx = 1;

            for (Info info : infos) {
                if (idx > 100) {
                    break;
                }
                A[idx++][i] = info.num;

                if (idx > 100) {
                    break;
                }
                A[idx++][i] = info.count;
            }

            while (idx <= 100) {
                A[idx++][i] = 0;
            }
        }
    }
}