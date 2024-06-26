package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1525">백준 1525번 - 해시 : 퍼즐</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1525%EB%B2%88-%ED%8D%BC%EC%A6%90">velog</a>
 * @since 2024-06-25
 */
public class BJ_1525 {

    static String goal = "123456780";
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }

        String init = sb.toString();
        map.put(init, 0);

        System.out.println(bfs(init));
    }

    private static int bfs(String init) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<String> qu = new ArrayDeque<>();
        qu.offer(init);

        while (!qu.isEmpty()) {
            String now = qu.poll();

            int count = map.get(now);

            if (now.equals(goal)) {
                return count;
            }

            int zero = now.indexOf("0"); //현재 0(빈칸)의 위치
            /**
             * 1차원 위치에서 2차원 위치 변환 공식
             * 행 x = 1차원 위치 / 열의 개수
             * 열 y = 1차원 위치 % 열의 개수
             */
            int x = zero / 3;
            int y = zero % 3;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {

                    /**
                     * 2차원 위치에서 1차원 위치 변환 공식
                     * 1차원 위치 = (행 * 열의 개수) + 열
                     */
                    int nextZero = nx * 3 + ny; //다음 0(빈칸)이 될 위치

                    //현재 빈 칸과 주변의 칸 swap
                    String next = swap(now, zero, nextZero);

                    if (!map.containsKey(next)) {
                        map.put(next, count + 1);
                        qu.offer(next);
                    }
                }
            }
        }

        return -1;
    }

    private static String swap(String s, int i, int j) {

        char[] charArray = s.toCharArray();

        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;

        return new String(charArray);
    }
}
