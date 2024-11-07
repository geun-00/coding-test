package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/19535">백준 19535번 - 트리 : ㄷㄷㄷㅈ</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-19535%EB%B2%88-%E3%84%B7%E3%84%B7%E3%84%B7%E3%85%88">velog</a>
 *
 * @since 2024-11-04
 */
public class BJ_19535 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        long d = 0, g = 0;

        //ㅈ 모양 판별
        for (int i = 1; i <= n; i++) {
            if (tree[i].size() >= 3) {

                long m = tree[i].size();

                //연결된 간선 중 3개를 고르는 경우의 수
                g += (m * (m - 1) * (m - 2)) / 6;
            }
        }

        //ㄷ 모양 판별
        for (int i = 1; i <= n; i++) {

            if (tree[i].size() >= 2) {

                for (int adj : tree[i]) {

                    //i < adj = 중복 계산 방지
                    if (i < adj && tree[adj].size() >= 2) {

                        d += (long) (tree[i].size() - 1) * (tree[adj].size() - 1);
                    }
                }
            }
        }

        if (d == g * 3) {
            System.out.println("DUDUDUNGA");
        } else if (d > g * 3) {
            System.out.println("D");
        } else {
            System.out.println("G");
        }
    }
}