package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2108">백준 2108번 - 구현 : 통계학</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2108%EB%B2%88-%ED%86%B5%EA%B3%84%ED%95%99">velog</a>
 * @since 2024-11-08
 */
public class BJ_2108 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] counting = new int[8001];

        //최댓값, 최솟값
        int min = 4001, max = -4001;

        //최빈값 중 첫번째, 두번째로 작은 값
        int first = 4001, second = 4001;

        //최빈값의 빈도수
        int maxFreq = 0;

        double sum = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            sum += num;
            counting[num + 4000]++;

            //최댓값, 최솟값 갱신
            min = Math.min(min, num);
            max = Math.max(max, num);

            //최빈값이 유일한 경우
            if (counting[num + 4000] > maxFreq) {
                maxFreq = counting[num + 4000];
                first = num;
                second = 4001; //두번째 초기화
            }
            //최빈값이 여러 개일 경우
            else if (counting[num + 4000] == maxFreq) {

                //첫번째로 작은 최빈값보다 작은 경우
                //기존 첫번째는 두번째가 되고, 첫번째는 새로운 수
                if (num < first) {
                    second = first;
                    first = num;
                }
                //두번째로 작은 최빈값보다 작은 경우
                //두번째 새로운 수
                else if (num < second) {
                    second = num;
                }
            }
        }

        //산술평균
        sb.append(Math.round(sum / n)).append("\n");

        //중앙값
        int count = 0;
        for (int i = 0; i <= 8000; i++) {
            count += counting[i];

            //작은 수부터 순서대로 개수를 셌을 때 중간이 되면 그 수는 중앙값
            if (count >= (n + 1) / 2) {
                sb.append(i - 4000).append("\n");
                break;
            }
        }

        //최빈값
        sb.append(second == 4001 ? first : second).append("\n");

        //범위
        sb.append(max - min);

        System.out.print(sb);
    }
}
