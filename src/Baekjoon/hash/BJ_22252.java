package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/22252">백준 22252번 - 해시 : 정보 상인 호석</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-22252%EB%B2%88-%EC%A0%95%EB%B3%B4-%EC%83%81%EC%9D%B8-%ED%98%B8%EC%84%9D">velog</a>
 *
 * @since 2024-09-09
 */
public class BJ_22252 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine());

        HashMap<String, PriorityQueue<Integer>> infos = new HashMap<>();

        long count = 0;

        while (q-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int k = Integer.parseInt(st.nextToken());

            //고릴라가 정보를 얻는 경우
            if (command == 1) {
                PriorityQueue<Integer> info = infos.getOrDefault(name, new PriorityQueue<>(Collections.reverseOrder()));

                //k개의 정보를 얻음
                for (int i = 0; i < k; i++) {
                    int num = Integer.parseInt(st.nextToken());
                    info.offer(num);
                }
                infos.put(name, info);
            }
            //호석이가 거래하는 경우
            else {
                PriorityQueue<Integer> info = infos.get(name);
                if (info == null) {
                    continue;
                }

                //고릴라가 가진 정보보다 거래하려는 정보가 더 많을 때, 고릴라가 가진 모든 정보 구매
                if (k >= info.size()) {
                    while (!info.isEmpty()) {
                        count += info.poll();
                    }
                }
                //아닌 경우
                else {
                    for (int i = 0; i < k; i++) {
                        count += info.poll();
                    }
                }

            }
        }

        System.out.println(count);
    }
}