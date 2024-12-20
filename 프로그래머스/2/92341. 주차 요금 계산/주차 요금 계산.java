import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Fee fee = new Fee(fees[0], fees[1], fees[2], fees[3]);

        TreeMap<String, Car> cars = new TreeMap<>();

        for (String record : records) {
            String[] tokens = record.split(" ");

            int time = parseTime(tokens[0]);
            String carNumber = tokens[1];
            boolean isIn = tokens[2].equals("IN");

            if (!cars.containsKey(carNumber)) {
                cars.put(carNumber, new Car(fee));
            }

            Car car = cars.get(carNumber);

            if (isIn) {
                car.in(time);
            } else {
                car.out(time);
            }
        }

        int endTime = parseTime("23:59");
        for (Car car : cars.values()) {
            car.out(endTime);
        }

        int[] ans = new int[cars.size()];

        for (int i = 0; i < ans.length; i++) {
            Map.Entry<String, Car> entry = cars.pollFirstEntry();

            Car car = entry.getValue();

            ans[i] = car.getCost();
        }

        return ans;
    }
    
    public int parseTime(String s) {
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3));

        return hour * 60 + minute;
    }
    
    static class Fee {

        int baseTime, baseFee;
        int unitTime, unitFee;

        public Fee(int baseTime, int baseFee, int unitTime, int unitFee) {
            this.baseTime = baseTime;
            this.baseFee = baseFee;
            this.unitTime = unitTime;
            this.unitFee = unitFee;
        }

        public int calculateCost(int time) {

            int fee = baseFee;

            time -= baseTime;
            while (time > 0) {
                fee += unitFee;
                time -= unitTime;
            }

            return fee;
        }
    }

    static class Car {

        Fee fee;
        int inTime;
        int totalCost;
        int totalTime;
        boolean isOut;

        public Car(Fee fee) {
            this.fee = fee;
        }

        public void in(int time) {
            this.isOut = false;
            this.inTime = time;
        }

        public void out(int outTime) {
            if (isOut) return;

            this.isOut = true;
            this.totalTime += (outTime - inTime);
        }

        public int getCost() {
            return fee.calculateCost(totalTime);
        }
    }
}