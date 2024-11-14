import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, Integer> map = new TreeMap<>();

        String input;

        int count = 0;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            map.put(input, map.getOrDefault(input, 0) + 1);
            count++;
        }

        StringBuilder sb = new StringBuilder();

        for (String s : map.keySet()) {

            int t = map.get(s);

            sb
                    .append(s)
                    .append(" ")
                    .append(String.format("%.4f", (t * 100.0) / count))
                    .append("\n");
        }

        System.out.print(sb);
    }
}