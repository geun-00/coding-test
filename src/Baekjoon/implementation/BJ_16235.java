package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16235">백준 16235번 - 구현 : 나무 재테크</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16235%EB%B2%88-%EB%82%98%EB%AC%B4-%EC%9E%AC%ED%85%8C%ED%81%AC">velog</a>
 * @since 2024-08-26
 */
public class BJ_16235 {

    static int n;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[][] nutrient; //양분
    static int[][] s2d2; //겨울마다 추가되는 양분
    static PriorityQueue<Integer>[][] map; //각 칸의 나무들 나이
    static Queue<Tree> deadTrees = new ArrayDeque<>(); //죽는 나무 목록

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); //처음에 심은 나무 수
        int k = Integer.parseInt(st.nextToken()); //진행 연도수

        nutrient = new int[n + 1][n + 1];
        s2d2 = new int[n + 1][n + 1];
        map = new PriorityQueue[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                nutrient[i][j] = 5;
                s2d2[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            map[x][y].offer(z);
        }

        while (k-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                result += map[i][j].size();
            }
        }
        System.out.println(result);
    }

    private static void spring() {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                PriorityQueue<Integer> qu = map[i][j];
                int size = qu.size();

                ArrayList<Integer> temp = new ArrayList<>(); //양분을 먹고 나이가 1 증가한 나무 = 살아남은 나무

                for (int k = 0; k < size; k++) {
                    int age = qu.poll();

                    if (nutrient[i][j] >= age) {
                        nutrient[i][j] -= age;
                        temp.add(age + 1);
                    } else {
                        deadTrees.add(new Tree(i, j, age)); //제거할 나무 목록 추가
                    }
                }

                //살아남은 나무들 추가
                for (int num : temp) {
                    qu.offer(num);
                }
            }
        }
    }

    private static void summer() {

        while (!deadTrees.isEmpty()) {
            Tree tree = deadTrees.poll();

            int x = tree.x;
            int y = tree.y;

            nutrient[x][y] += tree.age / 2;
        }
    }

    private static void fall() {

        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {

                for (int age : map[x][y]) {

                    if (age % 5 == 0) { //5의 배수일 때만

                        for (int k = 0; k < 8; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx > 0 && ny > 0 && nx <= n && ny <= n) {
                                map[nx][ny].offer(1); //나이가 1인 나무 추가
                            }
                        }
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nutrient[i][j] += s2d2[i][j];
            }
        }
    }

    static class Tree {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}