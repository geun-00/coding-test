package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2531">백준 2531번 - 투 포인터 : 회전 초밥</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2531%EB%B2%88-%ED%9A%8C%EC%A0%84-%EC%B4%88%EB%B0%A5">velog</a>
 * @since 2024-11-11
 */
public class BJ_2531 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0; //Set을 대신할 먹을 수 있는 초밥의 가짓수
        int[] sushi = new int[d + 1];

        //초기 윈도우 설정
        for (int i = 0; i < k; i++) {
            if (sushi[arr[i]] == 0) {
                count++;
            }
            sushi[arr[i]]++;
        }

        int max = count + (sushi[c] == 0 ? 1 : 0);

        for (int i = 1; i < n; i++) {

            int addIdx = (i + k - 1) % n;
            int removeIdx = i - 1;

            //앞부분 제거
            sushi[arr[removeIdx]]--;
            if (sushi[arr[removeIdx]] == 0) {
                count--;
            }

            //뒷부분 추가
            sushi[arr[addIdx]]++;
            if (sushi[arr[addIdx]] == 1) {
                count++;
            }

            max = Math.max(max, count + (sushi[c] == 0 ? 1 : 0));
        }

        System.out.println(max);
    }
}