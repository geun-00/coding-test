import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
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

            if (this.freq == o.freq) {
                if (this.word.length() == o.word.length()) {
                    return this.word.compareTo(o.word);
                }
                return o.word.length() - this.word.length();
            }

            return o.freq - this.freq;
        }
    }
}