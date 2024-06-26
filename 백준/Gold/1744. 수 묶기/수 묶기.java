import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        negative.sort(null);
        positive.sort(Collections.reverseOrder());

        int sum = 0;

        for (int i = 0; i < positive.size(); i++) {
            if (i + 1 < positive.size() && positive.get(i) != 1 && positive.get(i + 1) != 1) {
                sum += positive.get(i++) * positive.get(i);
            } else {
                sum += positive.get(i);
            }
        }

        for (int i = 0; i < negative.size(); i++) {
            if (i + 1 < negative.size()) {
                sum += negative.get(i++) * negative.get(i);
            } else {
                sum += negative.get(i);
            }
        }

        System.out.println(sum);
    }
}
