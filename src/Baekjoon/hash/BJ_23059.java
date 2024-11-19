package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/23059">백준 23059번 - 해시 : 리그 오브 레게노</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-23059%EB%B2%88-%EB%A6%AC%EA%B7%B8-%EC%98%A4%EB%B8%8C-%EB%A0%88%EA%B2%8C%EB%85%B8">velog</a>
 *
 * @since 2024-11-18
 */
public class BJ_23059 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> inDegree = new HashMap<>();
        HashMap<String, ArrayList<String>> items = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String a = st.nextToken();
            String b = st.nextToken();

            items.putIfAbsent(a, new ArrayList<>());
            items.putIfAbsent(b, new ArrayList<>());

            items.get(a).add(b);

            inDegree.put(b, inDegree.getOrDefault(b, 0) + 1);
            inDegree.putIfAbsent(a, 0);
        }

        PriorityQueue<String> buy = new PriorityQueue<>();
        ArrayDeque<String> qu = new ArrayDeque<>();

        for (String s : inDegree.keySet()) {
            if (inDegree.get(s) == 0) {
                buy.offer(s);
                qu.offer(s);
            }
        }

        StringBuilder sb = new StringBuilder();

        int count = 0;

        while (!qu.isEmpty()) {

            while (!buy.isEmpty()) {
                sb.append(buy.poll()).append("\n");
            }

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                count++;
                String now = qu.poll();

                for (String next : items.get(now)) {

                    inDegree.put(next, inDegree.get(next) - 1);

                    if (inDegree.get(next) == 0) {
                        buy.offer(next);
                        qu.offer(next);
                    }
                }
            }
        }

        System.out.print(count == items.size() ? sb : -1);
    }
}