package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3745">백준 3745번 - 이분 탐색 : 오름세</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3745%EB%B2%88-%EC%98%A4%EB%A6%84%EC%84%B8">velog</a>
 * @since 2024-11-06
 */
public class BJ_3745 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {

            int n = Integer.parseInt(input.trim());

            StringTokenizer st = new StringTokenizer(br.readLine());

            ArrayList<Integer> lis = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                int num = Integer.parseInt(st.nextToken());

                int left = 0;
                int right = lis.size() - 1;

                //Lower Bound
                while (left <= right) {

                    int mid = (left + right) / 2;

                    if (lis.get(mid) < num) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (left < lis.size()) {
                    lis.set(left, num);
                } else {
                    lis.add(num);
                }
            }

            sb.append(lis.size()).append("\n");
        }

        System.out.print(sb);
    }
}