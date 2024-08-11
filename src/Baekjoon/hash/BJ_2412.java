package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2412">백준 2412번 - 해시 : 암벽 등반</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2412%EB%B2%88-%EC%95%94%EB%B2%BD-%EB%93%B1%EB%B0%98">velog</a>
 * @since 2024-08-09
 */
public class BJ_2412 {

    static HashSet<String> dents = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dents.add(x + "," + y);
        }

        System.out.println(bfs(t));
    }

    private static int bfs(int t) {

        Queue<Dent> qu = new ArrayDeque<>();
        qu.offer(new Dent(0, 0, 0));

        //100만 x 20만 = 메모리 초과
//        boolean[][] visit = new boolean[1_000_001][t + 1];
//        visit[0][0] = true;

        HashSet<String> visit = new HashSet<>();
        visit.add("0,0");

        while (!qu.isEmpty()) {
            Dent now = qu.poll();

            if (now.y == t) {
                return now.moves;
            }

            for (int x = now.x - 2; x <= now.x + 2; x++) {
                for (int y = now.y - 2; y <= now.y + 2; y++) {

                    if (x < 0 || y < 0) {
                        continue;
                    }

                    String target = x + "," + y;

                    if (dents.contains(target) && visit.add(target)) {
                        qu.offer(new Dent(x, y, now.moves + 1));
                    }
                }
            }

/*
            //BFS 내에서 계속 n번 탐색하여 시간 초과
            for (Dent dent : dents) {
                if (Math.abs(now.x - dent.x) <= 2 && Math.abs(now.y - dent.y) <= 2) {
                    String target = dent.x + "," + dent.y;
                    if (visit.add(target)) {
                        qu.offer(new Dent(dent.x, dent.y, now.moves + 1));
                    }
                }
            }
*/
        }

        return -1;
    }

    static class Dent {
        int x, y, moves;

        public Dent(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }
}
