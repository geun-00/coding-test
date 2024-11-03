import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Room[] rooms;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long h = Long.parseLong(st.nextToken());

        rooms = new Room[n];

        long right = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            boolean isMonster = Integer.parseInt(st.nextToken()) == 1;
            long atk = Long.parseLong(st.nextToken());
            long hp = Long.parseLong(st.nextToken());

            rooms[i] = new Room(isMonster, atk, hp);
            if (isMonster) {
                right += atk * hp;
            }
        }

        long left = 1;
        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            Knight knight = new Knight(mid, mid, h);

            boolean possible = goDungeon(knight);

            if (possible) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean goDungeon(Knight knight) {

        for (Room room : rooms) {

            if (!room.isMonster) {
                knight.increase(room.atk, room.hp);
                continue;
            }

            long atkCnt = room.hp / knight.atk;
            if (room.hp % knight.atk != 0) {
                atkCnt++;
            }

            if (!knight.decrease(room.atk * (atkCnt - 1))) {
                return false;
            }
        }

        return true;
    }

    static class Room {

        boolean isMonster;
        long atk, hp;

        public Room(boolean isMonster, long atk, long hp) {
            this.isMonster = isMonster;
            this.atk = atk;
            this.hp = hp;
        }
    }

    static class Knight {

        long maxHP, curHP;
        long atk;

        public Knight(long maxHP, long curHP, long atk) {
            this.maxHP = maxHP;
            this.curHP = curHP;
            this.atk = atk;
        }

        public void increase(long atk, long hp) {
            this.atk += atk;
            this.curHP = Math.min(maxHP, curHP + hp);
        }

        public boolean decrease(long damage) {
            if (damage >= curHP) {
                return false;
            }
            curHP -= damage;
            return true;
        }
    }
}
