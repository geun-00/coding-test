package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12886">백준 12886번 - 돌 그룹</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12886%EB%B2%88-%EB%8F%8C-%EA%B7%B8%EB%A3%B9">velog</a>
 * @since 2025-03-19
 */
public class BJ_12886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int total = A + B + C;

        // 1
        boolean[][] visit = new boolean[1501][1501];
        visit[A][B] = true;
        visit[A][C] = true;
        visit[B][C] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{A, B});

        while (!qu.isEmpty()) {
            int[] stone = qu.poll();
            int a = stone[0];
            int b = stone[1];
            int c = total - (a + b);

            // 2
            if (a == b && b == c) {
                System.out.println(1);
                return;
            }

            // 3
            int[][] pairs = {{a, b}, {a, c}, {b, c}};
            for (int[] pair : pairs) {
                if (pair[0] != pair[1]) {
                    int x = pair[0];
                    int y = pair[1];

                    int nx = (x > y) ? x - y : x + x;
                    int ny = (x > y) ? y + y : y - x;

                    if (!visit[nx][ny]) {
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println(0);
    }
}
