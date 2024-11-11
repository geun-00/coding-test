package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20920">백준 20920번 - 해시 : 영단어 암기는 괴로워</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20920%EB%B2%88-%EC%98%81%EB%8B%A8%EC%96%B4-%EC%95%94%EA%B8%B0%EB%8A%94-%EA%B4%B4%EB%A1%9C%EC%9B%8C">velog</a>
 * @since 2024-11-10
 */
public class BJ_11478 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            if (s.length() >= m) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        ArrayList<Word> list = new ArrayList<>();

        for (String s : map.keySet()) {
            list.add(new Word(s, map.get(s)));
        }

        list.sort(null);

        StringBuilder sb = new StringBuilder();
        for (Word word : list) {
            sb.append(word.word).append("\n");
        }

        System.out.print(sb);
    }

    static class Word implements Comparable<Word>{

        String word;
        int freq;

        public Word(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public int compareTo(Word o) {

            //나온 횟수가 같다면
            if (this.freq == o.freq) {

                //나온 횟수, 단어의 길이가 같다면
                if (this.word.length() == o.word.length()) {
                    return this.word.compareTo(o.word);
                }

                return o.word.length() - this.word.length();
            }

            //자주 나오는 단어 순
            return o.freq - this.freq;
        }
    }
}
