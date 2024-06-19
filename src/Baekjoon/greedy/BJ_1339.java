package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <a href = "https://www.acmicpc.net/problem/1339">백준 - 그리디 : 단어 수학</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1339%EB%B2%88-%EB%8B%A8%EC%96%B4-%EC%88%98%ED%95%99">velog</a>
 * @since 2024-06-19
 */
public class BJ_1339 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        int[] alphaWeight = new int[26]; //알파벳별 가중치

        for (String word : words) {

            int len = word.length();

            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);

                alphaWeight[ch - 'A'] += (int) Math.pow(10, len - i - 1); //해당 자릿수가 몇 번 나오는지 계산
            }
        }

        Arrays.sort(alphaWeight);

        int num = 9;
        int result = 0;

        for (int i = 25; i >= 0; i--) {

            if (alphaWeight[i] == 0) {
                break;
            }

            result += alphaWeight[i] * num--; //높은 자릿수부터 높은 숫자를 할당하여 누적합 계산
        }

        System.out.println(result);
    }
}
