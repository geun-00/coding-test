import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        Word[] words = new Word[n - m + 1];

        for (int i = m; i <= n; i++) {
            StringBuilder text = new StringBuilder();

            for (char c : String.valueOf(i).toCharArray()) {
                text.append(arr[c - '0']);
            }

            words[i - m] = new Word(text.toString().trim(), i);
        }

        Arrays.sort(words, Comparator.comparing(a -> a.text));

        int total = words.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= (total / 10); i++) {
            for (int j = 0; j < 10; j++) {
                if (i * 10 + j >= total) {
                    break;
                }
                sb.append(words[i * 10 + j].num).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Word {
        String text;
        int num;

        public Word(String text, int num) {
            this.text = text;
            this.num = num;
        }
    }
}