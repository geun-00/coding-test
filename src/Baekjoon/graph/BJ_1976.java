package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1976">백준 1976번 - 그래프 탐색 : 여행 가자</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1976%EB%B2%88-%EC%97%AC%ED%96%89-%EA%B0%80%EC%9E%90">velog</a>
 * @since 2024-07-27
 */
public class BJ_1976 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int first = find(Integer.parseInt(st.nextToken()));

        for (int i = 0; i < m - 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (find(num) != first) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
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
