package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/15686">백준 15686번 - 치킨 배달</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-15686%EB%B2%88-%EC%B9%98%ED%82%A8-%EB%B0%B0%EB%8B%AC">velog</a>
 * @since 2024-06-20
 */
public class BJ_15686 {

    static int[][] map;
    static int n, m;
    static ArrayList<Point> chicken = new ArrayList<>(); //전체 치킨집
    static ArrayList<Point> house = new ArrayList<>(); //전체 집
    static int min = Integer.MAX_VALUE;
    static int[] arr; //치킨집들 중 m개를 고른 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                } else if (map[i][j] == 1) {
                    house.add(new Point(i, j));
                }
            }
        }

        solve(0,0);

        System.out.println(min);
    }

    private static void solve(int start, int depth) {
        if (depth == m) {
            min = Math.min(min, getMin());
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            arr[depth] = i;
            solve(i + 1, depth + 1);
        }
    }

    private static int getMin() {

        int sum = 0;

        //모든 집에서 m개의 치킨집을 탐색해 최소 거리 누적합
        for (Point h : house) { //모든 집 탐색

            int dist = Integer.MAX_VALUE;

            for (int i = 0; i < m; i++) { //선택된 m개의 치킨집 탐색
                Point c = chicken.get(arr[i]);

                int temp = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);

                dist = Math.min(dist, temp); //치킨집 중 가장 가까운 거리
            }

            sum += dist;
        }

        return sum;
    }
}
