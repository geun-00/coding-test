package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5021">백준 5021번 - 해시 : 왕위 계승</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5021%EB%B2%88-%EC%99%95%EC%9C%84-%EA%B3%84%EC%8A%B9">velog</a>
 *
 * @since 2024-10-20
 */
public class BJ_5021 {

    static HashMap<String, Parent> family = new HashMap<>(); //자식의 부모 정보
    static HashMap<String, Double> blood = new HashMap<>();  //각 사람의 혈통

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String king = br.readLine();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String father = st.nextToken();
            String mother = st.nextToken();

            family.put(child, new Parent(father, mother));

            //혈통 초기화
            blood.put(child, -1d);
            blood.put(father, -1d);
            blood.put(mother, -1d);
        }

        //왕의 혈통 1 초기화
        blood.put(king, 1d);

        //각 사람의 혈통 정보 구해놓기
        for (String s : blood.keySet()) {
            dfs(s);
        }

        String candidate = br.readLine();
        double max = blood.get(candidate);

        for (int i = 0; i < m - 1; i++) {
            String s = br.readLine();

            if (blood.getOrDefault(s, -1d) > max) {
                max = blood.get(s);
                candidate = s;
            }
        }

        System.out.println(candidate);
    }

    private static double dfs(String child) {

        if (blood.get(child) != -1) {
            return blood.get(child);
        }

        if (family.get(child) == null) {
            blood.put(child, 0d);
            return 0;
        }

        double father = dfs(family.get(child).father);
        double mother = dfs(family.get(child).mother);

        blood.put(child, (father + mother) / 2);

        return blood.get(child);
    }

    static class Parent {

        String father, mother;

        public Parent(String father, String mother) {
            this.father = father;
            this.mother = mother;
        }
    }
}
