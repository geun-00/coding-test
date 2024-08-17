package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href = "https://www.acmicpc.net/problem/16934">백준 16934번 - 해시 : 게임 닉네임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16934%EB%B2%88-%EA%B2%8C%EC%9E%84-%EB%8B%89%EB%84%A4%EC%9E%84">velog</a>
 * @since 2024-08-16
 */
public class BJ_16934 {

    static HashMap<String, Integer> countMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            sb.append(trie.insert(name)).append("\n");
        }

        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> child;

        public Node() {
            child = new HashMap<>();
        }
    }

    static class Trie {

        Node node;

        public Trie() {
            this.node = new Node();
        }

        String insert(String name) {

            Node now = this.node;

            boolean newChild = false;
            int temp = 0;

            for (int i = 0; i < name.length(); i++) {

                char ch = name.charAt(i);

                if (!now.child.containsKey(ch)) {

                    now.child.put(ch, new Node());

                    if (!newChild) { //새로운 경로로 가는 경우
                        newChild = true;
                        temp = i + 1;
                    }
                }

                now = now.child.get(ch);
            }

            //처음 나온 이름의 경우
            if (!countMap.containsKey(name)) {
                countMap.put(name, 1);
                //마지막 예제처럼 어떤 이름의 전체는 아예 다른 이름의 일부가 될 수 있다.
                //그런 경우 새로운 자식이 생기지 않기 때문에 닉네임 그대로 출력
                return newChild ? name.substring(0, temp) : name;
            }
            //2번 이상 나온 이름의 경우
            else {
                countMap.put(name, countMap.get(name) + 1);
                return name + countMap.get(name);
            }
        }
    }
}