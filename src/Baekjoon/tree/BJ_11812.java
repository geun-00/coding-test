package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11812">백준 11812번 - 트리 : K진 트리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11812%EB%B2%88-K%EC%A7%84-%ED%8A%B8%EB%A6%AC">velog</a>
 *
 * @since 2024-10-18
 */
public class BJ_11812 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (k == 1) {
                sb.append(Math.abs(x - y)).append("\n");
                continue;
            }

            long dist = 0;
            while (x != y) {
                //더 밑에 있는 노드의 부모 노드 구하기
                if (x > y) {
                    x = getParent(x, k);
                } else {
                    y = getParent(y, k);
                }
                dist++;
            }
            sb.append(dist).append("\n");

        }

        System.out.print(sb);
    }

    private static long getParent(long node, int k) {
        return (node - 2) / k + 1;
    }
}