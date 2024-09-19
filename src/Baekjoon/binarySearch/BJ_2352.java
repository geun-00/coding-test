package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2352">백준 2352번 - 이분 탐색 : 반도체 설계</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2352%EB%B2%88-%EB%B0%98%EB%8F%84%EC%B2%B4-%EC%84%A4%EA%B3%84">velog</a>
 * @since 2024-09-17
 */
public class BJ_2352 {

    static ArrayList<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] port = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            port[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(port[0]);

        for (int i = 1; i < n; i++) {

            int pos = binarySearch(port[i]);

            if (pos < lis.size()) {
                lis.set(pos, port[i]);
            } else {
                lis.add(port[i]);
            }
        }

        System.out.println(lis.size());
    }

    private static int binarySearch(int val) {

        int s = 0;
        int e = lis.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;
            if (lis.get(mid) < val) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return s;
    }
}
