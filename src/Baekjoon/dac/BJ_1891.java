package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1891">백준 1891번 - 분할 정복 : 사분면</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1891%EB%B2%88-%EC%82%AC%EB%B6%84%EB%A9%B4">velog</a>
 * @since 2025-01-17
 */
public class BJ_1891 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        String num = st.nextToken();

        st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        long size = 1L << (d - 1);
        long[] pos = findPos(0, 0, size, num, 0);

        long nx = pos[0] - y;
        long ny = pos[1] + x;

        if (nx < 0 || ny < 0 || nx >= size * 2 || ny >= size * 2) {
            System.out.println(-1);
            return;
        }

        System.out.println(findAns(nx, ny, size, d));
    }

    private static long[] findPos(long x, long y, long size, String num, int index) {

        if (index == num.length()) {
            return new long[]{x, y};
        }

        long half = size / 2;

        switch (num.charAt(index)) {
            case '1': return findPos(x, y + size, half, num, index + 1);
            case '2': return findPos(x, y, half, num, index + 1);
            case '3': return findPos(x + size, y, half, num, index + 1);
            case '4': return findPos(x + size, y + size, half, num, index + 1);
        }

        return null;
    }

    private static String findAns(long x, long y, long size, int d) {

        if (d == 0) {
            return "";
        }

        long half = size / 2;

        if (x < size && y >= size) return "1" + findAns(x, y - size, half, d - 1);
        else if (x < size && y < size) return "2" + findAns(x, y, half, d - 1);
        else if (x >= size && y < size) return "3" + findAns(x - size, y, half, d - 1);
        else return "4" + findAns(x - size, y - size, half, d - 1);
    }
}