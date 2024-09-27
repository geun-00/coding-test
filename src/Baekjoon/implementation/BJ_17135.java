package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17135">백준 17135번 - 구현 : 캐슬 디펜스</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17135%EB%B2%88-%EC%BA%90%EC%8A%AC-%EB%94%94%ED%8E%9C%EC%8A%A4">velog</a>
 *
 * @since 2024-09-20
 */
public class BJ_17135 {

    static int n, m, d;
    static int[][] map;
    static int[] archers = new int[3];
    static ArrayList<Point> enemies = new ArrayList<>();
    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    enemies.add(new Point(i, j));
                }
            }
        }

        solve(0, 0);

        System.out.println(max);
    }

    private static void solve(int start, int depth) {
        if (depth == 3) {
            max = Math.max(max, attack(copy(enemies)));
            return;
        }

        for (int i = start; i < m; i++) {
            archers[depth] = i;
            solve(i + 1, depth + 1);
        }
    }

    private static int attack(ArrayList<Point> enemies) {

        int count = 0;

        //모든 적이 제외될 때까지
        while (!enemies.isEmpty()) {

            //equals & hashCode 필수
            HashSet<Point> targets = new HashSet<>();

            //3명의 궁수마다 제거할 수 있는 적 구하기
            for (int i = 0; i < 3; i++) {

                PriorityQueue<Enemy> qu = new PriorityQueue<>();

                for (Point e : enemies) {
                    int dist = Math.abs(e.x - n) + Math.abs(e.y - archers[i]);

                    if (dist <= d) {
                        qu.offer(new Enemy(e.x, e.y, dist));
                    }
                }

                //제거할 수 있는 가장 가까운 적 타겟에 저장
                if (!qu.isEmpty()) {
                    Enemy e = qu.poll();
                    targets.add(new Point(e.x, e.y));
                }
            }

            //타겟에 저장된 적을 적 목록에서 제거
            for (Point target : targets) {
                enemies.removeIf(e -> e.x == target.x && e.y == target.y);
                count++;
            }

            //공격이 끝나고 적 이동
            moveEnemies(enemies);
        }

        return count;
    }

    private static void moveEnemies(ArrayList<Point> enemies) {

        Iterator<Point> iter = enemies.iterator();

        while (iter.hasNext()) {
            Point e = iter.next();
            e.x += 1;
            if (e.x == n) {
                iter.remove();
            }
        }
    }

    private static ArrayList<Point> copy(ArrayList<Point> enemies) {

        ArrayList<Point> temp = new ArrayList<>();
        for (Point e : enemies) {
            temp.add(new Point(e.x, e.y));
        }
        return temp;
    }

    static class Enemy implements Comparable<Enemy> {
        int x, y, d;

        public Enemy(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Enemy o) {
            if (this.d == o.d) {
                return this.y - o.y;
            }
            return this.d - o.d;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}