package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4195">백준 4195번 - 해시 : 친구 네트워크</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4195%EB%B2%88-%EC%B9%9C%EA%B5%AC-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC">velog</a>
 * @since 2024-06-21
 */
public class BJ_4195 {

    static HashMap<String, String> parent;
    static HashMap<String, Integer> size;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            parent = new HashMap<>();
            size = new HashMap<>();

            int f = Integer.parseInt(br.readLine());

            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String f1 = st.nextToken();
                String f2 = st.nextToken();

                //자기 자신으로 초기화
                if (!parent.containsKey(f1)) {
                    parent.put(f1, f1);
                    size.put(f1, 1);
                }

                //자기 자신으로 초기화
                if (!parent.containsKey(f2)) {
                    parent.put(f2, f2);
                    size.put(f2, 1);
                }

                union(f1, f2);
                sb.append(size.get(find(f2))).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void union(String f1, String f2) {

        //루트 노드 검색
        f1 = find(f1);
        f2 = find(f2);

        //값이 다르면 두 요소를 연결
        if (!f1.equals(f2)) {
            parent.put(f1, f2);
            size.put(f2, size.get(f1) + size.get(f2));
        }
    }

    private static String find(String f) {
        if (f.equals(parent.get(f))) {
            return f;
        }

        String root = find(parent.get(f)); //재귀 호출로 루트 노트 검색
        parent.put(f, root); //중요, 경로 압축 : 루트 노드를 찾으면서 거친 모든 노드들이 루트 노드와 연결됨

        return root;
    }
}
