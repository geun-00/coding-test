package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/21276">백준 21276번 - 해시 : 계보 복원가 호석</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21276%EB%B2%88-%EA%B3%84%EB%B3%B4-%EB%B3%B5%EC%9B%90%EA%B0%80-%ED%98%B8%EC%84%9D">velog</a>
 *
 * @since 2024-08-02
 */
public class BJ_2179 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashMap<String, ArrayList<String>> parentToChild = new HashMap<>(); //부모들의 자식 목록
        HashMap<String, ArrayList<String>> result = new HashMap<>(); //최종 결과
        HashMap<String, Integer> inDegree = new HashMap<>(); //진입 차수
        ArrayList<String> people = new ArrayList<>(); //사람들 목록

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            String name = st.nextToken();

            people.add(name);
            parentToChild.put(name, new ArrayList<>());
            result.put(name, new ArrayList<>());
            inDegree.put(name, 0);
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken(); //x
            String parent = st.nextToken(); //y

            parentToChild.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1); //자식 진입차수 증가
        }

        ArrayList<String> root = new ArrayList<>(); //시조 목록
        Queue<String> qu = new ArrayDeque<>();

        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                root.add(key);
                qu.offer(key);
            }
        }

        //가문의 개수와 각 가문의 시조들 출력
        root.sort(null);
        sb.append(root.size()).append("\n");
        for (String r : root) {
            sb.append(r).append(" ");
        }
        sb.append("\n");

        //위상 정렬
        while (!qu.isEmpty()) {

            String parent = qu.poll();

            for (String child : parentToChild.get(parent)) {
                inDegree.put(child, inDegree.get(child) - 1);

                if (inDegree.get(child) == 0) {
                    result.get(parent).add(child);
                    qu.offer(child);
                }
            }
        }

        //사전순 사람의 이름과 자식의 수, 자식들의 이름 출력
        people.sort(null);

        for (String parent : people) {

            ArrayList<String> children = result.get(parent);
            children.sort(null);

            sb
                    .append(parent)
                    .append(" ")
                    .append(children.size())
                    .append(" ");

            for (String child : children) {
                sb.append(child).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
