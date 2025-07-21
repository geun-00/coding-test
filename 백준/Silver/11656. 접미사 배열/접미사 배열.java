import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        String[] arr = new String[s.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.substring(i);
        }

        Arrays.sort(arr, String::compareTo);

        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str).append("\n");
        }

        System.out.print(sb);
    }
}