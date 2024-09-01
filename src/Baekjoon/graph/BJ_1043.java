package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1043">백준 1043번 - 그래프 탐색 : 거짓말</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1043%EB%B2%88-%EA%B1%B0%EC%A7%93%EB%A7%90">velog</a>
 * @since 2024-08-29
 */
public class BJ_1043 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        int[] trues = new int[t]; //진실을 아는 사람

        for (int i = 0; i < t; i++) {
            trues[i] = Integer.parseInt(st.nextToken());
        }

        int[][] party = new int[m][50]; //파티마다 오는 사람의 수와 번호

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int people = Integer.parseInt(st.nextToken());
            for (int j = 0; j < people; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            int first = party[i][0];

            for (int j = 1; j < party[i].length; j++) {
                if (party[i][j] == 0) {
                    break;
                }

                union(first, party[i][j]); //각 파티의 파티원들을 하나로 묶기
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {

            int first = party[i][0]; //각 파티의 파티원들은 하나의 그룹이 되었기 때문에 첫 번호만 알면 된다.
            boolean flag = true;

            for (int tr : trues) {
                if (find(first) == find(tr)) { //진실을 아는 사람 그룹에 속해 있으면 안된다.
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}