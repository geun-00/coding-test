package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3020">백준 3020번 - 이분 탐색 : 개똥벌레</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3020%EB%B2%88-%EA%B0%9C%EB%98%A5%EB%B2%8C%EB%A0%88">velog</a>
 * @since 2024-08-22
 */
public class BJ_3020 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] up = new int[n / 2]; //석순
        int[] down = new int[n / 2]; //종유석

        for (int i = 0; i < n / 2; i++) {
            up[i] = Integer.parseInt(br.readLine());
            down[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = Integer.MAX_VALUE; //파괴해야 하는 장애물
        int count = 0; //구간의 수

        for (int i = 1; i <= h; i++) {

            //i 구간을 날아갈 때 파괴해야 하는 석순의 개수
            int upCount = up.length - binarySearch(up, i);

            //i 구간을 날아갈 때 파괴해야 하는 종유석의 개수
            int downCount = down.length - binarySearch(down, h - i + 1);

            int temp = upCount + downCount;

            if (temp < min) {
                min = temp;
                count = 1;
            } else if (temp == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }

    private static int binarySearch(int[] arr, int target) {

        int s = 0;
        int e = arr.length;

        while (s < e) {

            int mid = (s + e) / 2;

            if (arr[mid] < target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }

        return s;
    }
}
