package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1083">백준 1083번 - 그리디 : 소트</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1083%EB%B2%88-%EC%86%8C%ED%8A%B8">velog</a>
 * @since 2024-11-03
 */
public class BJ_1083 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {

            int maxIdx = i;

            //현재 위치 이후에 S 범위 이내 가장 큰 값의 위치 찾기
            for (int j = i + 1; j <= i + s && j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }

            //이미 큰 수가 앞에 있으면 S번 소모할 필요 없음
            if (maxIdx == i) continue;

            //최대 S 만큼 뒤에 있는 큰 수를 앞으로 가져오기
            for (int j = maxIdx; j > i; j--) {

                //swap
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;

                s--;
            }

            if (s <= 0) {
                break;
            }
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
