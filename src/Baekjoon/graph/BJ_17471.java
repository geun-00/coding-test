package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17471">백준 17471번 - 그래프 탐색 : 게리맨더링</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17471%EB%B2%88-%EA%B2%8C%EB%A6%AC%EB%A7%A8%EB%8D%94%EB%A7%81">velog</a>
 * @since 2024-10-30
 */
public class BJ_17471 {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[] people;
    static int[] area;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        graph = new ArrayList[n];
        people = new int[n];
        area = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken()) - 1;
                graph[i].add(num);
            }
        }

        int min = INF;

        //두 개 구역을 나누는 경우의 수 비트로 확인
        //반드시 두 개로 나누어야 하기 때문에 0과 마지막은 제외
        for (int bit = 1; bit < (1 << n) - 1; bit++) {

            //1과 0으로 구분
            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) != 0) {
                    area[i] = 1;
                } else {
                    area[i] = 0;
                }
            }

            if (isConnected(1) && isConnected(0)) {
                int diff = Math.abs(getPeople(1) - getPeople(0));
                min = Math.min(min, diff);
            }
        }

        System.out.println(min == INF ? -1 : min);
    }

    private static boolean isConnected(int a) {

        int start = -1;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            if (area[i] == a) {
                start = i;
                temp++;
            }
        }

        visit = new boolean[n];

        //실제 연결된 구역 개수와 비교
        return dfs(start, a) == temp;
    }

    private static int dfs(int now, int a) {

        visit[now] = true;
        int count = 1;

        for (int next : graph[now]) {
            if (!visit[next] && area[next] == a) {
                count += dfs(next, a);
            }
        }

        return count;
    }

    private static int getPeople(int a) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (area[i] == a) {
                sum += people[i];
            }
        }
        return sum;
    }
}